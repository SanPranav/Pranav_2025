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
      <p>Auto Clicker (10 cookies): <button onclick="buyItem('autoClicker')">Buy</button></p>
    </div>
    <div class="item">
      <p>Double Click Power (20 cookies): <button onclick="buyItem('doubleClick')">Buy</button></p>
    </div>
  </div>

  <script>
    let cookieCount = 0;
    let clickPower = 1;
    let autoClickerCount = 0;

    // Update Cookie Count
    function updateCookieCount() {
      document.getElementById('cookie-count').innerText = cookieCount;
    }

    // Clicking on the cookie
    document.getElementById('cookie').addEventListener('click', function() {
      cookieCount += clickPower;
      updateCookieCount();
      playClickSound();
    });

    // Play click sound
    function playClickSound() {
      const clickSound = document.getElementById('click-sound');
      clickSound.play();
    }

    // Shop: Buying items
    function buyItem(item) {
      if (item === 'autoClicker' && cookieCount >= 10) {
        cookieCount -= 10;
        autoClickerCount++;
        setInterval(autoClick, 1000);  // Auto Click every second
      } else if (item === 'doubleClick' && cookieCount >= 20) {
        cookieCount -= 20;
        clickPower *= 2;
      }
      updateCookieCount();
    }

    // Auto Clicker function
    function autoClick() {
      cookieCount += autoClickerCount;
      updateCookieCount();
    }
  </script>
</body>
</html>