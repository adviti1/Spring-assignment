const firebaseConfig = {
  apiKey: "YOUR_KEY",
  authDomain: "YOUR_DOMAIN",
  projectId: "YOUR_PROJECT"
};

firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();

function login() {
  const email = email.value;
  const password = password.value;

  auth.signInWithEmailAndPassword(email, password)
    .then(() => alert("Logged in"))
    .catch(err => alert(err.message));
}

function toggleCart() {
  const cart = document.getElementById("cart");
  cart.style.display = cart.style.display === "none" ? "block" : "none";
  loadCart();
}

function addProduct() {
  fetch("http://localhost:8080/api/cart", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      productId: 1,
      name: "Laptop",
      quantity: 1,
      price: 80000
    })
  }).then(loadCart);
}

function loadCart() {
  fetch("http://localhost:8080/api/cart")
    .then(res => res.json())
    .then(data => {
      const ul = document.getElementById("cartItems");
      ul.innerHTML = "";
      data.forEach(item => {
        ul.innerHTML += `
          <li>
            ${item.name} - Qty: ${item.quantity}
            <button onclick="removeItem(${item.productId})">X</button>
          </li>`;
      });
    });
}

function removeItem(id) {
  fetch(`http://localhost:8080/api/cart/${id}`, {
    method: "DELETE"
  }).then(loadCart);
}
