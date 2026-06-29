<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - FoodExpress</title>
    <!-- FontAwesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

/* CSS Variables based on Vibrant Cravings Design System */
:root {
    --color-surface: #fff8f6;
    --color-surface-dim: #eed5cd;
    --color-surface-bright: #fff8f6;
    --color-surface-container-lowest: #ffffff;
    --color-surface-container-low: #fff1ed;
    --color-surface-container: #ffe9e3;
    --color-surface-container-high: #fde3db;
    --color-surface-container-highest: #f7ddd5;
    
    --color-on-surface: #261814;
    --color-on-surface-variant: #594139;
    --color-inverse-surface: #3c2d28;
    --color-inverse-on-surface: #ffede8;
    
    --color-outline: #8d7168;
    --color-outline-variant: #e1bfb5;
    
    --color-primary: #ab3500; /* Zest Orange */
    --color-on-primary: #ffffff;
    --color-primary-container: #ffdbd0;
    --color-on-primary-container: #5f1900;
    --color-primary-fixed: #ffdbd0;
    --color-primary-fixed-variant: #832600;
    
    --color-secondary: #006a62; /* Fresh Green */
    --color-on-secondary: #ffffff;
    --color-secondary-container: #70f8e8;
    --color-on-secondary-container: #007168;
    
    --color-tertiary: #00677e;
    --color-on-tertiary: #ffffff;
    --color-tertiary-container: #00a7cb;
    
    --color-error: #ba1a1a;
    --color-on-error: #ffffff;
    --color-error-container: #ffdad6;
    --color-on-error-container: #93000a;
    
    --color-background: #fff8f6;
    --color-on-background: #261814;

    /* Typography Scale */
    --font-family: 'Poppins', sans-serif;
    
    /* Rounded Shapes */
    --radius-sm: 0.25rem;     /* 4px */
    --radius-default: 0.5rem; /* 8px - chips/tags */
    --radius-md: 0.75rem;     /* 12px */
    --radius-lg: 1rem;        /* 16px - cards, inputs, containers */
    --radius-xl: 1.5rem;      /* 24px */
    --radius-pill: 9999px;    /* buttons, search bar */

    /* Spacing */
    --space-xs: 4px;
    --space-base: 8px;
    --space-sm: 12px;
    --space-md: 24px;
    --space-lg: 48px;
    --space-xl: 64px;
    --container-max: 1200px;
    
    /* Shadows & Elevations */
    --shadow-level-1: 0px 4px 20px rgba(0, 0, 0, 0.05); /* Surfaces */
    --shadow-level-2: 0px 8px 30px rgba(0, 0, 0, 0.08); /* Hover / Interactive */
    --shadow-level-3: 0px 12px 40px rgba(0, 0, 0, 0.12); /* Drawer / Modals */
}

/* Global Reset */
*, *::before, *::after {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    font-family: var(--font-family);
    background-color: var(--color-background);
    color: var(--color-on-background);
    line-height: 1.6;
    -webkit-font-smoothing: antialiased;
}

a {
    color: inherit;
    text-decoration: none;
}

button, input, select, textarea {
    font-family: inherit;
}

/* Scrollbar Customization */
::-webkit-scrollbar {
    width: 8px;
}
::-webkit-scrollbar-track {
    background: var(--color-surface-container);
}
::-webkit-scrollbar-thumb {
    background: var(--color-outline-variant);
    border-radius: var(--radius-pill);
}
::-webkit-scrollbar-thumb:hover {
    background: var(--color-outline);
}

/* Container */
.container {
    width: 100%;
    max-width: var(--container-max);
    margin: 0 auto;
    padding: 0 var(--space-md);
}

/* Grid Layouts */
.grid {
    display: grid;
    gap: var(--space-md);
}

.grid-3 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
}

@media(min-width: 600px) {
    .grid-3 {
        grid-template-columns: repeat(2, minmax(0, 1fr));
    }
}

@media(min-width: 900px) {
    .grid-3 {
        grid-template-columns: repeat(3, minmax(0, 1fr));
    }
}

/* Typography styles */
.display-lg {
    font-size: 48px;
    font-weight: 700;
    line-height: 1.2;
    letter-spacing: -0.02em;
}

.headline-lg {
    font-size: 32px;
    font-weight: 600;
    line-height: 1.3;
}

@media(max-width: 600px) {
    .headline-lg {
        font-size: 24px;
    }
}

.headline-md {
    font-size: 20px;
    font-weight: 600;
    line-height: 1.4;
}

.body-lg {
    font-size: 18px;
    font-weight: 400;
    line-height: 1.6;
}

.body-md {
    font-size: 16px;
    font-weight: 400;
    line-height: 1.6;
}

.label-md {
    font-size: 14px;
    font-weight: 500;
    line-height: 1.4;
    letter-spacing: 0.01em;
}

.label-sm {
    font-size: 12px;
    font-weight: 600;
    line-height: 1.2;
}

/* Header & Navbar */
.navbar {
    background-color: var(--color-surface-container-lowest);
    border-bottom: 1px solid var(--color-surface-container-highest);
    position: sticky;
    top: 0;
    z-index: 100;
    padding: var(--space-sm) 0;
    box-shadow: var(--shadow-level-1);
}

.navbar .nav-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: var(--space-md);
}

.logo {
    display: flex;
    align-items: center;
    gap: var(--space-xs);
    font-size: 24px;
    font-weight: 700;
    color: var(--color-primary);
}

.logo span {
    color: var(--color-secondary);
}

.nav-search {
    flex: 1;
    max-width: 500px;
    position: relative;
    display: flex;
    align-items: center;
}

.nav-search input {
    width: 100%;
    padding: 10px 20px 10px 45px;
    border-radius: var(--radius-pill);
    border: 1px solid var(--color-outline-variant);
    background-color: var(--color-surface-container-low);
    font-size: 14px;
    transition: all 0.3s ease;
}

.nav-search input:focus {
    outline: none;
    border-color: var(--color-primary);
    background-color: var(--color-surface-container-lowest);
    box-shadow: 0 0 0 3px rgba(171, 53, 0, 0.15);
}

.nav-search i {
    position: absolute;
    left: 18px;
    color: var(--color-outline);
}

.nav-actions {
    display: flex;
    align-items: center;
    gap: var(--space-md);
}

.nav-address {
    font-size: 13px;
    color: var(--color-on-surface-variant);
    max-width: 200px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: flex;
    align-items: center;
    gap: var(--space-xs);
}

@media(max-width: 768px) {
    .nav-address {
        display: none;
    }
}

.cart-btn {
    position: relative;
    background: none;
    border: none;
    cursor: pointer;
    font-size: 20px;
    color: var(--color-on-surface);
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    border-radius: var(--radius-pill);
    transition: background-color 0.2s ease;
}

.cart-btn:hover {
    background-color: var(--color-surface-container);
}

.cart-badge {
    position: absolute;
    top: 2px;
    right: 2px;
    background-color: var(--color-primary);
    color: var(--color-on-primary);
    font-size: 10px;
    font-weight: 700;
    min-width: 18px;
    height: 18px;
    border-radius: var(--radius-pill);
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 2px;
    border: 2px solid var(--color-surface-container-lowest);
}

.user-profile {
    display: flex;
    align-items: center;
    gap: var(--space-xs);
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    padding: 6px 12px;
    border-radius: var(--radius-pill);
    transition: background-color 0.2s ease;
}

.user-profile:hover {
    background-color: var(--color-surface-container);
}

.user-avatar {
    width: 28px;
    height: 28px;
    background-color: var(--color-primary-container);
    color: var(--color-on-primary-container);
    border-radius: var(--radius-pill);
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: 600;
    font-size: 12px;
}

/* Buttons */
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    gap: var(--space-xs);
    padding: 12px 28px;
    border-radius: var(--radius-pill);
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    border: none;
}

.btn-primary {
    background-color: var(--color-primary);
    color: var(--color-on-primary);
    box-shadow: 0 4px 12px rgba(171, 53, 0, 0.2);
}

.btn-primary:hover {
    background-color: #c43e00;
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(171, 53, 0, 0.3);
}

.btn-primary:active {
    transform: translateY(0);
}

.btn-secondary {
    background-color: var(--color-surface-container-lowest);
    color: var(--color-primary);
    border: 2px solid var(--color-primary);
}

.btn-secondary:hover {
    background-color: var(--color-surface-container-low);
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.btn-secondary:active {
    transform: translateY(0);
}

.btn-sm {
    padding: 8px 16px;
    font-size: 12px;
}

.btn-block {
    width: 100%;
}

/* Badges & Chips */
.badge {
    display: inline-flex;
    align-items: center;
    gap: 4px;
    padding: 4px 8px;
    border-radius: var(--radius-default);
    font-size: 12px;
    font-weight: 600;
}

.badge-rating {
    background-color: #fff9e6;
    color: #b38600;
}

.badge-delivery {
    background-color: var(--color-surface-container);
    color: var(--color-on-surface-variant);
}

.badge-veg {
    border: 1.5px solid var(--color-secondary);
    color: var(--color-secondary);
    background-color: rgba(0, 106, 98, 0.05);
    padding: 2px 6px;
    font-size: 10px;
    border-radius: 4px;
}

.badge-nonveg {
    border: 1.5px solid var(--color-error);
    color: var(--color-error);
    background-color: rgba(186, 26, 26, 0.05);
    padding: 2px 6px;
    font-size: 10px;
    border-radius: 4px;
}

/* Category Filter Pills */
.categories-container {
    margin: var(--space-md) 0;
    display: flex;
    gap: var(--space-sm);
    overflow-x: auto;
    padding: 8px 4px;
    scroll-behavior: smooth;
    scrollbar-width: none;
}

.categories-container::-webkit-scrollbar {
    display: none;
}

.category-pill {
    padding: 10px 20px;
    border-radius: var(--radius-pill);
    background-color: var(--color-surface-container-low);
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    border: 1px solid var(--color-outline-variant);
    transition: all 0.2s ease;
    white-space: nowrap;
    display: flex;
    align-items: center;
    gap: 6px;
}

.category-pill:hover {
    background-color: var(--color-surface-container);
    border-color: var(--color-outline);
}

.category-pill.active {
    background-color: var(--color-primary);
    color: var(--color-on-primary);
    border-color: var(--color-primary);
    box-shadow: 0 4px 10px rgba(171, 53, 0, 0.2);
}

/* Auth Pages (Login & Register) */
.auth-wrapper {
    min-height: calc(100vh - 80px);
    display: flex;
    align-items: center;
    justify-content: center;
    padding: var(--space-md) 0;
    background: radial-gradient(circle at top right, var(--color-surface-container), var(--color-background));
}

.auth-card {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-lg);
    box-shadow: var(--shadow-level-2);
    width: 100%;
    max-width: 450px;
    padding: var(--space-lg) var(--space-md);
    border: 1px solid var(--color-surface-container-high);
}

.auth-header {
    text-align: center;
    margin-bottom: var(--space-md);
}

.auth-header h2 {
    color: var(--color-primary);
    margin-bottom: var(--space-xs);
}

.auth-header p {
    color: var(--color-on-surface-variant);
    font-size: 14px;
}

/* Forms */
.form-group {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
    gap: 6px;
}

.form-group label {
    font-size: 14px;
    font-weight: 500;
    color: var(--color-on-surface);
}

.form-control {
    width: 100%;
    padding: 12px 16px;
    border-radius: var(--radius-lg);
    border: 1px solid var(--color-outline-variant);
    background-color: var(--color-surface-container-lowest);
    font-size: 14px;
    transition: all 0.3s ease;
    color: var(--color-on-surface);
}

.form-control:focus {
    outline: none;
    border-color: var(--color-primary);
    box-shadow: 0 0 0 3px rgba(171, 53, 0, 0.15);
}

.form-footer {
    text-align: center;
    margin-top: var(--space-md);
    font-size: 14px;
    color: var(--color-on-surface-variant);
}

.form-footer a {
    color: var(--color-primary);
    font-weight: 600;
}

.form-footer a:hover {
    text-decoration: underline;
}

/* Alert Boxes */
.alert {
    padding: var(--space-sm) var(--space-md);
    border-radius: var(--radius-md);
    font-size: 14px;
    font-weight: 500;
    margin-bottom: var(--space-md);
    display: flex;
    align-items: center;
    gap: var(--space-xs);
}

.alert-danger {
    background-color: var(--color-error-container);
    color: var(--color-on-error-container);
    border: 1px solid rgba(186, 26, 26, 0.2);
}

.alert-success {
    background-color: var(--color-secondary-container);
    color: var(--color-on-secondary-container);
    border: 1px solid rgba(0, 106, 98, 0.2);
}

/* Hero Section */
.hero-section {
    background: linear-gradient(135deg, #ffdbd0 0%, #ffede8 100%);
    border-radius: var(--radius-xl);
    padding: var(--space-lg) var(--space-md);
    margin-bottom: var(--space-lg);
    position: relative;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    gap: 12px;
}

.hero-section::after {
    content: '';
    position: absolute;
    right: -100px;
    bottom: -100px;
    width: 300px;
    height: 300px;
    background-color: rgba(171, 53, 0, 0.05);
    border-radius: var(--radius-pill);
    pointer-events: none;
}

.hero-section h1 {
    color: var(--color-on-primary-fixed-variant);
    max-width: 600px;
}

.hero-section p {
    color: var(--color-on-surface-variant);
    max-width: 500px;
}

/* Restaurant Card List */
.section-title {
    margin-bottom: var(--space-md);
    color: var(--color-on-surface);
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.restaurant-card {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-lg);
    overflow: hidden;
    box-shadow: var(--shadow-level-1);
    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    border: 1px solid var(--color-surface-container-high);
    cursor: pointer;
    display: flex;
    flex-direction: column;
    height: 100%;
}

.restaurant-card:hover {
    transform: translateY(-6px);
    box-shadow: var(--shadow-level-2);
    border-color: var(--color-outline-variant);
}

.restaurant-image-wrapper {
    width: 100%;
    height: 200px;
    overflow: hidden;
    position: relative;
}

.restaurant-image-wrapper img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.5s ease;
}

.restaurant-card:hover .restaurant-image-wrapper img {
    transform: scale(1.05);
}

.restaurant-info {
    padding: var(--space-md);
    display: flex;
    flex-direction: column;
    flex: 1;
    gap: 8px;
}

.restaurant-name {
    font-size: 18px;
    font-weight: 600;
    color: var(--color-on-surface);
}

.restaurant-desc {
    font-size: 14px;
    color: var(--color-on-surface-variant);
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 1.4;
    flex: 1;
}

.restaurant-meta {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 8px;
    padding-top: 12px;
    border-top: 1px solid var(--color-surface-container-highest);
}

/* Restaurant Details Page */
.restaurant-hero {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-xl);
    box-shadow: var(--shadow-level-1);
    padding: var(--space-md);
    margin-bottom: var(--space-lg);
    border: 1px solid var(--color-surface-container-high);
    display: flex;
    flex-direction: column;
    gap: var(--space-md);
}

@media(min-width: 768px) {
    .restaurant-hero {
        flex-direction: row;
        padding: var(--space-lg);
    }
}

.restaurant-hero-img {
    width: 100%;
    height: 200px;
    border-radius: var(--radius-lg);
    object-fit: cover;
}

@media(min-width: 768px) {
    .restaurant-hero-img {
        width: 300px;
        height: 200px;
    }
}

.restaurant-hero-details {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: var(--space-xs);
    flex: 1;
}

.restaurant-hero-meta {
    display: flex;
    flex-wrap: wrap;
    gap: var(--space-sm);
    margin-top: 8px;
}

/* Menu Section */
.menu-container {
    display: flex;
    flex-direction: column;
    gap: var(--space-lg);
    margin-bottom: var(--space-xl);
}

.category-section {
    display: flex;
    flex-direction: column;
    gap: var(--space-md);
}

.category-title {
    color: var(--color-on-surface);
    border-left: 4px solid var(--color-primary);
    padding-left: var(--space-sm);
    margin-bottom: 4px;
}

.menu-card {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-lg);
    border: 1px solid var(--color-surface-container-high);
    box-shadow: var(--shadow-level-1);
    overflow: hidden;
    display: flex;
    flex-direction: column;
    transition: all 0.2s ease;
}

.menu-card:hover {
    box-shadow: var(--shadow-level-2);
    border-color: var(--color-outline-variant);
}

@media(min-width: 600px) {
    .menu-card {
        flex-direction: row;
        height: 180px;
    }
}

.menu-img-wrapper {
    width: 100%;
    height: 150px;
    overflow: hidden;
    position: relative;
}

@media(min-width: 600px) {
    .menu-img-wrapper {
        width: 180px;
        height: 100%;
    }
}

.menu-img-wrapper img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.menu-details {
    padding: var(--space-md);
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    gap: 8px;
}

.menu-header-row {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    gap: var(--space-sm);
}

.menu-item-name {
    font-size: 16px;
    font-weight: 600;
    color: var(--color-on-surface);
}

.menu-item-desc {
    font-size: 13px;
    color: var(--color-on-surface-variant);
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 1.4;
}

.menu-bottom-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: auto;
}

.menu-item-price {
    font-size: 18px;
    font-weight: 700;
    color: var(--color-primary);
}

/* Floating Cart Drawer (Level 3 Elevation Overlay) */
.cart-drawer-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(38, 24, 20, 0.4);
    backdrop-filter: blur(4px);
    z-index: 1000;
    opacity: 0;
    visibility: hidden;
    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.cart-drawer-overlay.active {
    opacity: 1;
    visibility: visible;
}

.cart-drawer {
    position: fixed;
    top: 0;
    right: -400px;
    width: 100%;
    max-width: 400px;
    height: 100%;
    background-color: var(--color-surface-container-lowest);
    box-shadow: var(--shadow-level-3);
    z-index: 1001;
    display: flex;
    flex-direction: column;
    transition: right 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.cart-drawer-overlay.active .cart-drawer {
    right: 0;
}

.cart-drawer-header {
    padding: var(--space-md);
    border-bottom: 1px solid var(--color-surface-container-highest);
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.cart-drawer-close {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 20px;
    width: 32px;
    height: 32px;
    border-radius: var(--radius-pill);
    display: flex;
    align-items: center;
    justify-content: center;
    color: var(--color-on-surface-variant);
    transition: background-color 0.2s ease;
}

.cart-drawer-close:hover {
    background-color: var(--color-surface-container);
}

.cart-drawer-body {
    flex: 1;
    overflow-y: auto;
    padding: var(--space-md);
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
}

.cart-empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    color: var(--color-on-surface-variant);
    gap: var(--space-xs);
}

.cart-empty-state i {
    font-size: 48px;
    color: var(--color-outline-variant);
}

.cart-item {
    display: flex;
    gap: var(--space-sm);
    padding: var(--space-sm);
    background-color: var(--color-surface-container-low);
    border-radius: var(--radius-md);
    align-items: center;
}

.cart-item-details {
    flex: 1;
    display: flex;
    flex-direction: column;
    gap: 4px;
}

.cart-item-name {
    font-size: 14px;
    font-weight: 600;
    color: var(--color-on-surface);
}

.cart-item-price {
    font-size: 13px;
    font-weight: 700;
    color: var(--color-primary);
}

.quantity-controller {
    display: flex;
    align-items: center;
    border: 1px solid var(--color-outline-variant);
    border-radius: var(--radius-pill);
    overflow: hidden;
    background-color: var(--color-surface-container-lowest);
}

.quantity-controller button {
    background: none;
    border: none;
    width: 28px;
    height: 28px;
    cursor: pointer;
    font-weight: 600;
    color: var(--color-primary);
    transition: background-color 0.2s ease;
}

.quantity-controller button:hover {
    background-color: var(--color-surface-container);
}

.quantity-controller span {
    font-size: 13px;
    font-weight: 600;
    min-width: 24px;
    text-align: center;
    color: var(--color-on-surface);
}

.cart-drawer-footer {
    padding: var(--space-md);
    border-top: 1px solid var(--color-surface-container-highest);
    background-color: var(--color-surface-container-low);
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
}

.cart-summary-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 14px;
    font-weight: 500;
}

.cart-summary-total {
    font-size: 18px;
    font-weight: 700;
    color: var(--color-on-surface);
}

.cart-summary-total span {
    color: var(--color-primary);
}

/* Standalone Cart Page & Checkout Page */
.page-wrapper {
    padding: var(--space-lg) 0;
}

.cart-grid {
    display: grid;
    gap: var(--space-md);
    grid-template-columns: 1fr;
}

@media(min-width: 992px) {
    .cart-grid {
        grid-template-columns: 2fr 1fr;
    }
}

.cart-table-card, .summary-card, .checkout-card {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-lg);
    box-shadow: var(--shadow-level-1);
    border: 1px solid var(--color-surface-container-high);
    padding: var(--space-md);
}

.cart-list-items {
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
}

.cart-list-item {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
    padding-bottom: var(--space-sm);
    border-bottom: 1px solid var(--color-surface-container-highest);
    gap: var(--space-sm);
}

.cart-list-item-info {
    display: flex;
    align-items: center;
    gap: var(--space-sm);
    flex: 1;
    min-width: 200px;
}

.cart-list-item-img {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: var(--radius-default);
}

.cart-list-item-desc h4 {
    font-size: 15px;
    font-weight: 600;
}

.cart-list-item-desc p {
    font-size: 12px;
    color: var(--color-on-surface-variant);
}

.cart-list-item-actions {
    display: flex;
    align-items: center;
    gap: var(--space-md);
}

.cart-list-item-subtotal {
    font-size: 16px;
    font-weight: 700;
    color: var(--color-primary);
    min-width: 80px;
    text-align: right;
}

.cart-list-item-delete {
    background: none;
    border: none;
    color: var(--color-error);
    cursor: pointer;
    font-size: 16px;
    transition: transform 0.2s ease;
}

.cart-list-item-delete:hover {
    transform: scale(1.1);
}

.summary-card {
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
    height: fit-content;
}

.summary-card h3 {
    border-bottom: 1px solid var(--color-surface-container-highest);
    padding-bottom: 8px;
}

.summary-row {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    color: var(--color-on-surface-variant);
}

.summary-row-total {
    display: flex;
    justify-content: space-between;
    font-size: 18px;
    font-weight: 700;
    color: var(--color-on-surface);
    border-top: 1px solid var(--color-surface-container-highest);
    padding-top: var(--space-sm);
    margin-top: 8px;
}

/* Payment selection styling */
.payment-methods {
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
    margin: var(--space-sm) 0;
}

.payment-method-option {
    display: flex;
    align-items: center;
    gap: var(--space-sm);
    padding: 14px;
    border: 1px solid var(--color-outline-variant);
    border-radius: var(--radius-lg);
    cursor: pointer;
    transition: all 0.2s ease;
}

.payment-method-option input {
    accent-color: var(--color-primary);
    width: 18px;
    height: 18px;
}

.payment-method-option:hover {
    border-color: var(--color-outline);
    background-color: var(--color-surface-container-low);
}

.payment-method-option.selected {
    border-color: var(--color-primary);
    background-color: var(--color-surface-container);
}

/* Order History / Track Orders */
.order-history-card {
    background-color: var(--color-surface-container-lowest);
    border-radius: var(--radius-lg);
    box-shadow: var(--shadow-level-1);
    border: 1px solid var(--color-surface-container-high);
    padding: var(--space-md);
    margin-bottom: var(--space-md);
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
}

.order-history-header {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    border-bottom: 1px solid var(--color-surface-container-highest);
    padding-bottom: var(--space-sm);
    gap: 8px;
}

.order-id {
    font-weight: 600;
    color: var(--color-on-surface);
}

.order-date {
    font-size: 13px;
    color: var(--color-on-surface-variant);
}

.order-status-badge {
    padding: 4px 10px;
    border-radius: var(--radius-pill);
    font-size: 12px;
    font-weight: 600;
}

.status-pending {
    background-color: #fff1cc;
    color: #8a6d00;
}

.status-preparing {
    background-color: var(--color-primary-container);
    color: var(--color-on-primary-container);
}

.status-delivered {
    background-color: var(--color-secondary-container);
    color: var(--color-on-secondary-container);
}

.status-cancelled {
    background-color: var(--color-error-container);
    color: var(--color-on-error-container);
}

.order-details-summary {
    display: flex;
    flex-direction: column;
    gap: 6px;
    font-size: 14px;
}

.order-history-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top: 1px dashed var(--color-surface-container-highest);
    padding-top: var(--space-sm);
    margin-top: 8px;
}

.order-total-amount {
    font-weight: 700;
    color: var(--color-primary);
    font-size: 16px;
}

/* Footer Section */
.footer {
    background-color: var(--color-inverse-surface);
    color: var(--color-inverse-on-surface);
    padding: var(--space-xl) 0 var(--space-md);
    margin-top: var(--space-xl);
}

.footer-grid {
    display: grid;
    gap: var(--space-md);
    grid-template-columns: repeat(1, minmax(0, 1fr));
    margin-bottom: var(--space-lg);
}

@media(min-width: 768px) {
    .footer-grid {
        grid-template-columns: repeat(3, minmax(0, 1fr));
    }
}

.footer-col h3 {
    margin-bottom: var(--space-sm);
    color: var(--color-primary-fixed-dim);
}

.footer-col p {
    font-size: 14px;
    color: var(--color-outline-variant);
}

.footer-links {
    list-style: none;
}

.footer-links li {
    margin-bottom: 8px;
    font-size: 14px;
    color: var(--color-outline-variant);
}

.footer-links a:hover {
    color: var(--color-primary-fixed-dim);
}

.footer-bottom {
    text-align: center;
    padding-top: var(--space-md);
    border-top: 1px solid rgba(225, 191, 181, 0.1);
    font-size: 12px;
    color: var(--color-outline-variant);
}

</style>
</head>
<body>

    <div class="auth-wrapper">
        <div class="auth-card">
            <div class="auth-header">
                <div class="logo" style="justify-content: center; margin-bottom: var(--space-sm);">
                    <i class="fa fa-paper-plane"></i> Food<span>Express</span>
                </div>
                <h2 class="headline-md">Create Account</h2>
                <p class="body-sm">Sign up to explore top-rated restaurants near you.</p>
            </div>

            <!-- Error Notifications -->
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="alert alert-danger">
                    <i class="fa fa-exclamation-triangle"></i>
                    <div><%= error %></div>
                </div>
            <%
                }
            %>

            <!-- Registration Form -->
            <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" class="form-control" placeholder="John Doe" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="john@example.com" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="At least 6 characters" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" class="form-control" placeholder="10-digit mobile number" required>
                </div>

                <div class="form-group" style="margin-bottom: var(--space-md);">
                    <label for="address">Delivery Address</label>
                    <textarea id="address" name="address" class="form-control" rows="2" placeholder="Street name, Building, Flat No, Landmark" required></textarea>
                </div>

                <button type="submit" class="btn btn-primary btn-block">
                    Register <i class="fa fa-user-plus"></i>
                </button>
            </form>

            <div class="form-footer">
                Already have an account? <a href="${pageContext.request.contextPath}/login">Login Here</a>
            </div>
        </div>
    </div>

    <!-- Client-side script -->
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
