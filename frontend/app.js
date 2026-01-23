console.log("app.js loaded");

const API_BASE = "https://spring-backend-g8o8.onrender.com/api";


const state = {
  products: [],
  cartItems: []
};



const api = {
  async request(path, options = {}) {
    console.log("API:", API_BASE + path);

    const res = await fetch(API_BASE + path, {
      headers: { "Content-Type": "application/json" },
      ...options
    });

    if (!res.ok) {
      const text = await res.text();
      throw new Error(text || "API Error");
    }

    return res.status !== 204 ? res.json() : null;
  }
};


async function loadProducts() {
  try {
    state.products = await api.request("/products");
    renderProducts();
  } catch (err) {
    showError(err.message);
  }
}

async function loadCart() {
  try {
    state.cartItems = await api.request("/cart");
    renderCart();
  } catch (err) {
    console.error(err);
  }
}


function renderProducts() {
  const grid = document.getElementById("product-grid");
  const loading = document.getElementById("loading-state");
  const content = document.getElementById("content-state");

  grid.innerHTML = "";

  state.products.forEach(p => {
    grid.innerHTML += `
      <div class="product-card">
        <div class="product-image-container">
          <div class="price-tag">₹${p.price}</div>
        </div>

        <div class="product-info">
          <h3 class="product-title">${p.name}</h3>
          <p class="product-desc">Premium quality product</p>
          <button class="btn btn-primary" onclick="handleAddToCart(${p.id})">
            Add to Cart
          </button>
        </div>
      </div>
    `;
  });

  loading.style.display = "none";
  content.style.display = "block";
}


function renderCart() {
  const container = document.getElementById("cart-items-container");
  const footer = document.getElementById("cart-footer");
  const badge = document.getElementById("cart-badge");

  container.innerHTML = "";

  if (state.cartItems.length === 0) {
    container.innerHTML = "<p>Your cart is empty</p>";
    footer.style.display = "none";
    badge.style.display = "none";
    return;
  }

  let total = 0;
  let count = 0;

  state.cartItems.forEach(item => {
    total += item.product.price * item.quantity;
    count += item.quantity;

    container.innerHTML += `
      <div class="cart-item">
        <div class="cart-item-info">
          <strong>${item.product.name}</strong>

          <div class="quantity-controls">
            <button class="qty-btn" onclick="handleUpdateQty(${item.id}, ${item.quantity - 1})">-</button>
            <span class="qty-val">${item.quantity}</span>
            <button class="qty-btn" onclick="handleUpdateQty(${item.id}, ${item.quantity + 1})">+</button>
          </div>

          <button class="btn btn-outline" style="margin-top:8px"
            onclick="handleDelete(${item.id})">
            Remove
          </button>
        </div>
      </div>
    `;
  });

  document.getElementById("cart-total").innerText = `₹${total}`;
  badge.innerText = count;
  badge.style.display = "flex";
  footer.style.display = "block";
}


window.handleAddToCart = async (productId) => {
  await api.request(`/cart/add/${productId}`, { method: "POST" });
  await loadCart();
};

window.handleUpdateQty = async (cartItemId, qty) => {
  if (qty <= 0) {
    await handleDelete(cartItemId);
    return;
  }
  await api.request(`/cart/update/${cartItemId}?qty=${qty}`, { method: "PUT" });
  await loadCart();
};

window.handleDelete = async (cartItemId) => {
  await api.request(`/cart/delete/${cartItemId}`, { method: "DELETE" });
  await loadCart();
};

window.handleCheckout = async () => {
  await api.request("/cart/checkout", { method: "POST" });
  await loadCart();
};


function showError(msg) {
  document.getElementById("loading-state").style.display = "none";
  document.getElementById("error-state").style.display = "block";
  document.getElementById("error-message").innerText = msg;
}


function setupCartUI() {
  document.getElementById("cart-toggle-btn").onclick = () => {
    document.getElementById("cart-sidebar").classList.add("open");
    document.getElementById("cart-overlay").classList.add("open");
  };

  document.getElementById("close-cart-btn").onclick = closeCart;
  document.getElementById("cart-overlay").onclick = closeCart;
}

function closeCart() {
  document.getElementById("cart-sidebar").classList.remove("open");
  document.getElementById("cart-overlay").classList.remove("open");
}


document.addEventListener("DOMContentLoaded", () => {
  console.log("DOM ready");
  setupCartUI();
  loadProducts();
  loadCart();
});
