// document.addEventListener("DOMContentLoaded", () => {
//   const products = [
//     {
//       id: 1,
//       name: "Laptop",
//       description: "High-performance laptop",
//       price: "$1200",
//     },
//     {
//       id: 2,
//       name: "Graphics Card",
//       description: "Powerful GPU",
//       price: "$800",
//     },
//   ];

//   const productContainer = document.getElementById("products");
//   const sellerProductContainer = document.getElementById("seller-products");
//   const addComputerButton = document.getElementById("add-computer-button");
//   const sellButton = document.getElementById("sell-button");
//   const loginButton = document.getElementById("login-button");
//   const logoutButton = document.getElementById("logout-button");

//   function displayProducts(products, container) {
//     container.innerHTML = "";
//     products.forEach((product) => {
//       const productElement = document.createElement("div");
//       productElement.classList.add("product");
//       productElement.innerHTML = `
//                   <h3>${product.name}</h3>
//                   <p>${product.description}</p>
//                   <p><strong>${product.price}</strong></p>
//               `;
//       if (container === sellerProductContainer) {
//         const deleteButton = document.createElement("button");
//         deleteButton.innerText = "Delete";
//         deleteButton.onclick = () => deleteProduct(product.id);
//         productElement.appendChild(deleteButton);
//       }
//       container.appendChild(productElement);
//     });
//   }

//   displayProducts(products, productContainer);

//   window.startBuying = function () {
//     document.getElementById("product-list").style.display = "block";
//     document.getElementById("seller-actions").style.display = "none";
//   };

//   window.startSelling = function () {
//     document.getElementById("product-list").style.display = "none";
//     document.getElementById("seller-actions").style.display = "block";
//     displayProducts(products, sellerProductContainer);
//   };

//   window.addComputer = function () {
//     const newProduct = {
//       id: products.length + 1,
//       name: "New Computer",
//       description: "New description",
//       price: "$1000",
//     };
//     products.push(newProduct);
//     displayProducts(products, sellerProductContainer);
//   };

//   window.deleteProduct = function (productId) {
//     const index = products.findIndex((product) => product.id === productId);
//     if (index !== -1) {
//       products.splice(index, 1);
//       displayProducts(products, sellerProductContainer);
//     }
//   };

//   window.showLogin = function () {
//     document.getElementById("login-modal").style.display = "flex";
//   };

//   window.closeLogin = function () {
//     document.getElementById("login-modal").style.display = "none";
//   };

//   window.login = function (event) {
//     event.preventDefault(); // Prevent the form from submitting
//     const username = document.getElementById("username").value;
//     const password = document.getElementById("password").value;

//     // Here you can add logic to validate the username and password
//     // For demo purposes, we'll just assume any input is valid
//     if (username && password) {
//       alert("Logged in as " + username);
//       loginButton.style.display = "none";
//       logoutButton.style.display = "block";
//       addComputerButton.style.display = "block"; // Show Add Computer button
//       sellButton.style.display = "block"; // Show Sell button
//       closeLogin();
//     }
//   };

//   window.logout = function () {
//     loginButton.style.display = "block";
//     logoutButton.style.display = "none";
//     addComputerButton.style.display = "none"; // Hide Add Computer button
//     sellButton.style.display = "none"; // Hide Sell button
//   };
// });
