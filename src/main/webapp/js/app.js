document.addEventListener('DOMContentLoaded', () => {
    // 1. Initialize Cart Drawer Behaviors
    const cartOverlay = document.getElementById('cartDrawerOverlay');
    const cartToggleBtns = document.querySelectorAll('.js-cart-toggle');
    const cartCloseBtn = document.getElementById('cartDrawerClose');

    if (cartToggleBtns.length > 0 && cartOverlay) {
        cartToggleBtns.forEach(btn => {
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                openCart();
            });
        });
    }

    if (cartCloseBtn && cartOverlay) {
        cartCloseBtn.addEventListener('click', closeCart);
        cartOverlay.addEventListener('click', (e) => {
            if (e.target === cartOverlay) {
                closeCart();
            }
        });
    }

    function openCart() {
        if (cartOverlay) {
            cartOverlay.classList.add('active');
            fetchCartContents();
        }
    }

    function closeCart() {
        if (cartOverlay) {
            cartOverlay.classList.remove('active');
        }
    }

    // 2. Global AJAX Functions for Cart Management
    window.addToCart = function(menuId) {
        const contextPath = getContextPath();
        fetch(`${contextPath}/cart?action=add&menuId=${menuId}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                updateCartBadge(data.totalItems);
                openCart(); // Show drawer on successful addition
            } else {
                alert('Failed to add item to cart. Please login first.');
                if (data.redirect) {
                    window.location.href = data.redirect;
                }
            }
        })
        .catch(err => {
            console.error('Error adding to cart:', err);
            // Fallback for non-ajax endpoints
            window.location.reload();
        });
    };

    window.updateCartQuantity = function(menuId, quantity) {
        const contextPath = getContextPath();
        if (quantity <= 0) {
            window.removeFromCart(menuId);
            return;
        }
        
        fetch(`${contextPath}/cart?action=update&menuId=${menuId}&quantity=${quantity}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                updateCartBadge(data.totalItems);
                fetchCartContents(); // Refresh drawer content
                // If we are on the standalone cart page, reload it to update prices
                if (document.getElementById('standaloneCartPage')) {
                    window.location.reload();
                }
            }
        })
        .catch(err => console.error('Error updating quantity:', err));
    };

    window.removeFromCart = function(menuId) {
        const contextPath = getContextPath();
        fetch(`${contextPath}/cart?action=remove&menuId=${menuId}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                updateCartBadge(data.totalItems);
                fetchCartContents();
                if (document.getElementById('standaloneCartPage')) {
                    window.location.reload();
                }
            }
        })
        .catch(err => console.error('Error removing from cart:', err));
    };

    function updateCartBadge(count) {
        const badges = document.querySelectorAll('.cart-badge');
        badges.forEach(badge => {
            badge.textContent = count;
            if (count > 0) {
                badge.style.display = 'flex';
            } else {
                badge.style.display = 'none';
            }
        });
    }

    function fetchCartContents() {
        const drawerBody = document.getElementById('cartDrawerBody');
        const drawerSubtotal = document.getElementById('cartDrawerSubtotal');
        if (!drawerBody) return;

        const contextPath = getContextPath();
        fetch(`${contextPath}/cart?action=get`)
        .then(response => response.json())
        .then(data => {
            if (data.items && data.items.length > 0) {
                let html = '';
                data.items.forEach(item => {
                    html += `
                        <div class="cart-item">
                            <div class="cart-item-details">
                                <span class="cart-item-name">${item.foodName}</span>
                                <span class="cart-item-price">Rs. ${parseFloat(item.price).toFixed(2)}</span>
                            </div>
                            <div class="quantity-controller">
                                <button onclick="updateCartQuantity(${item.menuId}, ${item.quantity - 1})">-</button>
                                <span>${item.quantity}</span>
                                <button onclick="updateCartQuantity(${item.menuId}, ${item.quantity + 1})">+</button>
                            </div>
                        </div>
                    `;
                });
                drawerBody.innerHTML = html;
                if (drawerSubtotal) {
                    drawerSubtotal.textContent = `Rs. ${parseFloat(data.grandTotal).toFixed(2)}`;
                }
            } else {
                drawerBody.innerHTML = `
                    <div class="cart-empty-state">
                        <i class="fa fa-shopping-basket"></i>
                        <p class="body-md" style="font-weight: 500;">Your basket is empty</p>
                        <p class="label-sm" style="color: var(--color-on-surface-variant);">Add items to start your order</p>
                    </div>
                `;
                if (drawerSubtotal) {
                    drawerSubtotal.textContent = 'Rs. 0.00';
                }
            }
            updateCartBadge(data.totalItems || 0);
        })
        .catch(err => {
            console.error('Error fetching cart:', err);
            drawerBody.innerHTML = '<p class="label-md" style="color:var(--color-error); text-align:center;">Failed to load cart</p>';
        });
    }

    // 3. Client-side Real-time Search & Category Filters (Home Page)
    const searchInput = document.getElementById('restaurantSearch');
    const categoryPills = document.querySelectorAll('.category-pill');
    const restaurantCards = document.querySelectorAll('.js-restaurant-card');

    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            filterRestaurants();
        });
    }

    if (categoryPills.length > 0) {
        categoryPills.forEach(pill => {
            pill.addEventListener('click', () => {
                categoryPills.forEach(p => p.classList.remove('active'));
                pill.classList.add('active');
                filterRestaurants();
            });
        });
    }

    function filterRestaurants() {
        const query = searchInput ? searchInput.value.toLowerCase().trim() : '';
        
        let activeCategory = 'all';
        const activePill = document.querySelector('.category-pill.active');
        if (activePill) {
            activeCategory = activePill.getAttribute('data-category').toLowerCase();
        }

        restaurantCards.forEach(card => {
            const name = card.getAttribute('data-name').toLowerCase();
            const desc = card.getAttribute('data-desc').toLowerCase();
            const categories = card.getAttribute('data-categories').toLowerCase();

            const matchesSearch = name.includes(query) || desc.includes(query);
            const matchesCategory = activeCategory === 'all' || categories.includes(activeCategory);

            if (matchesSearch && matchesCategory) {
                card.style.display = 'flex';
            } else {
                card.style.display = 'none';
            }
        });
    }

    // 4. Form Validations (Login, Register & Checkout)
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const checkoutForm = document.getElementById('checkoutForm');

    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            const emailInput = loginForm.querySelector('input[type="email"]');
            const passwordInput = loginForm.querySelector('input[type="password"]');
            
            if (!emailInput.value.trim() || !passwordInput.value.trim()) {
                e.preventDefault();
                showFormError(loginForm, 'Email and Password are required.');
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', (e) => {
            const fullName = document.getElementById('fullName');
            const email = document.getElementById('email');
            const password = document.getElementById('password');
            const phone = document.getElementById('phone');
            const address = document.getElementById('address');
            
            let errors = [];
            
            if (!fullName.value.trim()) errors.push('Full Name is required.');
            if (!email.value.trim() || !validateEmail(email.value)) errors.push('Valid Email is required.');
            if (!password.value.trim() || password.value.length < 6) errors.push('Password must be at least 6 characters.');
            if (!phone.value.trim() || !/^\d{10}$/.test(phone.value.trim())) errors.push('Phone must be a valid 10-digit number.');
            if (!address.value.trim()) errors.push('Address is required.');

            if (errors.length > 0) {
                e.preventDefault();
                showFormError(registerForm, errors.join('<br>'));
            }
        });
    }

    if (checkoutForm) {
        checkoutForm.addEventListener('submit', (e) => {
            const address = document.getElementById('deliveryAddress');
            if (!address.value.trim()) {
                e.preventDefault();
                alert('Please enter a delivery address.');
            }
        });
        
        // Handle custom styling for checked payment method card
        const paymentRadioBtns = checkoutForm.querySelectorAll('input[name="paymentMethod"]');
        const paymentOptions = checkoutForm.querySelectorAll('.payment-method-option');
        
        paymentRadioBtns.forEach(radio => {
            radio.addEventListener('change', () => {
                paymentOptions.forEach(opt => opt.classList.remove('selected'));
                if (radio.checked) {
                    radio.closest('.payment-method-option').classList.add('selected');
                }
            });
        });
    }

    // Helper: Show Error Alert inside form
    function showFormError(form, htmlMsg) {
        let alertBox = form.querySelector('.alert-danger');
        if (!alertBox) {
            alertBox = document.createElement('div');
            alertBox.className = 'alert alert-danger';
            form.insertBefore(alertBox, form.firstChild);
        }
        alertBox.innerHTML = `<i class="fa fa-exclamation-triangle"></i> <div>${htmlMsg}</div>`;
        alertBox.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
    }

    // Helper: Validate Email Syntax
    function validateEmail(email) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    // Helper: Dynamically fetch context path from URL
    function getContextPath() {
        return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
    }
});
