---
layout: post
title: Cookie Clicker Game
description: Cookie Clicker Game
---

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cookie Clicker</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
    }
    #cookie {
      width: 200px;
      height: 200px;
      cursor: pointer;
    }
    #shop {
      margin-top: 20px;
    }
    .item {
      margin: 10px 0;
    }
    .item button {
      background-color: white; /* Default button color */
      color: black;
      border: none;
      padding: 5px 10px; /* Smaller padding */
      cursor: pointer;
      box-shadow: 0 0 10px red; /* Default box shadow color */
      border-radius: 10px; /* Rounded edges */
    }
    .item button.affordable {
      box-shadow: 0 0 10px green; /* Box shadow color when affordable */
    }
    .item button:disabled {
      background-color: grey; /* Disabled button color */
      cursor: not-allowed;
    }
  </style>
</head>
<body>
  <h1>Cookie Clicker</h1>
  <p>Cookies: <span id="cookie-count">0</span></p>
  <img id="cookie" src="{{site.baseurl}}/images/cookie-modified.png" alt="Cookie">
  <audio id="click-sound" src="{{site.baseurl}}/audio/points.wav" preload="auto"></audio>

  <div id="shop">
    <h2>Shop</h2>
    <div class="item">
      <p>Auto Clicker (10 cookies): <button id="buy-auto-clicker" onclick="buyItem('autoClicker')">Buy</button></p>
    </div>
    <div class="item">
      <p>Double Click Power (20 cookies): <button id="buy-double-click" onclick="buyItem('doubleClick')">Buy</button></p>
    </div>
    <div class="item">
      <p>Triple Click Power (50 cookies): <button id="buy-triple-click" onclick="buyItem('tripleClick')">Buy</button></p>
    </div>
    <div class="item">
      <p>Quadruple Click Power (100 cookies): <button id="buy-quadruple-click" onclick="buyItem('quadrupleClick')">Buy</button></p>
    </div>
  </div>

  <script>
    let cookieCount = 0;
    let clickPower = 1;
    let autoClickerCount = 0;

    // Update Cookie Count
    function updateCookieCount() {
      document.getElementById('cookie-count').innerText = cookieCount;
      updateButtonStates();
    }

    // Update Button States
    function updateButtonStates() {
      const autoClickerButton = document.getElementById('buy-auto-clicker');
      const doubleClickButton = document.getElementById('buy-double-click');
      const tripleClickButton = document.getElementById('buy-triple-click');
      const quadrupleClickButton = document.getElementById('buy-quadruple-click');

      autoClickerButton.disabled = cookieCount < 10;
      doubleClickButton.disabled = cookieCount < 20;
      tripleClickButton.disabled = cookieCount < 50;
      quadrupleClickButton.disabled = cookieCount < 100;

      autoClickerButton.classList.toggle('affordable', cookieCount >= 10);
      doubleClickButton.classList.toggle('affordable', cookieCount >= 20);
      tripleClickButton.classList.toggle('affordable', cookieCount >= 50);
      quadrupleClickButton.classList.toggle('affordable', cookieCount >= 100);
    }

    // Clicking on the cookie
    document.getElementById('cookie').addEventListener('click', function() {
      cookieCount += clickPower;
      document.getElementById('click-sound').play(); // Play click sound
      updateCookieCount();
    });

    // Shop: Buying items
    function buyItem(item) {
      if (item === 'autoClicker' && cookieCount >= 10) {
        cookieCount -= 10;
        autoClickerCount++;
        setInterval(autoClick, 1000);  // Auto Click every second
      } else if (item === 'doubleClick' && cookieCount >= 20) {
        cookieCount -= 20;
        clickPower *= 2;
      } else if (item === 'tripleClick' && cookieCount >= 50) {
        cookieCount -= 50;
        clickPower *= 3;
      } else if (item === 'quadrupleClick' && cookieCount >= 100) {
        cookieCount -= 100;
        clickPower *= 4;
      }
      updateCookieCount();
    }

    // Auto Clicker function
    function autoClick() {
      cookieCount += autoClickerCount;
      updateCookieCount();
    }

    // Initial button state update
    updateButtonStates();
  </script>
</body>
</html>