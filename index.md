---
layout: base
title: Student Home 
description: Home Page
hide: true
image: /images/mario_animation.png
---

<!-- Liquid:  statements -->
{% include nav/home.html %}
<!--- Concatenation of site URL to frontmatter image  --->
{% assign sprite_file = site.baseurl | append: page.image %}
<!--- Has is a list variable containing mario metadata for sprite --->
{% assign hash = site.data.mario_metadata %}  
<!--- Size width/height of Sprit images --->
{% assign pixels = 256 %}

<!--- HTML for page contains <p> tag named "Mario" and class properties for a "sprite"  -->

<p id="mario" class="sprite"></p>
  
<!--- Embedded Cascading Style Sheet (CSS) rules, 
        define how HTML elements look 
--->
<style>

  /*CSS style rules for the id and class of the sprite...
  */
  .sprite {
    height: {{pixels}}px;
    width: {{pixels}}px;
    background-image: url('{{sprite_file}}');
    background-repeat: no-repeat;
  }

  /*background position of sprite element
  */
  #mario {
    background-position: calc({{animations[0].col}} * {{pixels}} * -1px) calc({{animations[0].row}} * {{pixels}}* -1px);
  }
</style>

<!--- Embedded executable code--->
<script>
  ////////// convert YML hash to javascript key:value objects /////////

  var mario_metadata = {}; //key, value object
  {% for key in hash %}  
  
  var key = "{{key | first}}"  //key
  var values = {} //values object
  values["row"] = {{key.row}}
  values["col"] = {{key.col}}
  values["frames"] = {{key.frames}}
  mario_metadata[key] = values; //key with values added

  {% endfor %}

  ////////// game object for player /////////

  class Mario {
    constructor(meta_data) {
      this.tID = null;  //capture setInterval() task ID
      this.positionX = 0;  // current position of sprite in X direction
      this.currentSpeed = 0;
      this.marioElement = document.getElementById("mario"); //HTML element of sprite
      this.pixels = {{pixels}}; //pixel offset of images in the sprite, set by liquid constant
      this.interval = 100; //animation time interval
      this.obj = meta_data;
      this.marioElement.style.position = "absolute";
    }

    animate(obj, speed) {
      let frame = 0;
      const row = obj.row * this.pixels;
      this.currentSpeed = speed;

      this.tID = setInterval(() => {
        const col = (frame + obj.col) * this.pixels;
        this.marioElement.style.backgroundPosition = `-${col}px -${row}px`;
        this.marioElement.style.left = `${this.positionX}px`;

        this.positionX += speed;
        frame = (frame + 1) % obj.frames;

        const viewportWidth = window.innerWidth;
        if (this.positionX > viewportWidth - this.pixels) {
          document.documentElement.scrollLeft = this.positionX - viewportWidth + this.pixels;
        }
      }, this.interval);
    }

    startWalking() {
      this.stopAnimate();
      this.animate(this.obj["Walk"], 3);
    }

    startRunning() {
      this.stopAnimate();
      this.animate(this.obj["Run1"], 6);
    }

    startPuffing() {
      this.stopAnimate();
      this.animate(this.obj["Puff"], 0);
    }

    startCheering() {
      this.stopAnimate();
      this.animate(this.obj["Cheer"], 0);
    }

    startFlipping() {
      this.stopAnimate();
      this.animate(this.obj["Flip"], 0);
    }

    startResting() {
      this.stopAnimate();
      this.animate(this.obj["Rest"], 0);
    }

    stopAnimate() {
      clearInterval(this.tID);
    }
  }

  const mario = new Mario(mario_metadata);

  ////////// event control /////////

  window.addEventListener("keydown", (event) => {
    if (event.key === "ArrowRight") {
      event.preventDefault();
      if (event.repeat) {
        mario.startCheering();
      } else {
        if (mario.currentSpeed === 0) {
          mario.startWalking();
        } else if (mario.currentSpeed === 3) {
          mario.startRunning();
        }
      }
    } else if (event.key === "ArrowLeft") {
      event.preventDefault();
      if (event.repeat) {
        mario.stopAnimate();
      } else {
        mario.startPuffing();
      }
    }
  });

  //touch events that enable animations
  window.addEventListener("touchstart", (event) => {
    event.preventDefault(); // prevent default browser action
    if (event.touches[0].clientX > window.innerWidth / 2) {
      // move right
      if (mario.currentSpeed === 0) { // if at rest, go to walking
        mario.startWalking();
      } else if (mario.currentSpeed === 3) { // if walking, go to running
        mario.startRunning();
      }
    } else {
      // move left
      mario.startPuffing();
    }
  });

    // Stop Mario's animation when the window loses focus
  window.addEventListener("blur", () => {
    if (mario) {
      mario.stopAnimate();
    }
  });

  // Start Mario's flipping animation when the window gains focus
  window.addEventListener("focus", () => {
    if (mario) {
      mario.startFlipping();
    }
  });

  // Start Mario's resting animation on page load or refresh
  document.addEventListener("DOMContentLoaded", () => {
    // Adjust sprite size for high pixel density devices
    const scale = window.devicePixelRatio;
    const sprite = document.querySelector(".sprite");

    if (sprite) {
      sprite.style.transform = `scale(${0.2 * scale})`;
    }

    if (mario) {
      mario.startResting();
    }
  });
  
</script>

<section class="home-hero">
  <img src="{{site.baseurl}}/images/profile_picture.png" alt="Profile Picture" class="home-profile-image">
  <h1>Welcome to My Journey</h1>
  <p class="home-subtitle">My journey starts here.</p>

  <div class="home-grid">
    <section class="home-card" id="family">
      <h2>Family</h2>
      <p>Family of five: brother, mother, sister, father, and me.</p>
      <p>As the oldest, I take on responsibilities and set an example.</p>
    </section>

    <section class="home-card" id="coding-journey">
      <h2>Coding Journey</h2>
      <p>Started coding at a young age and have pursued it ever since.</p>
      <p>Completed CSSE 1 and CSSE 2, building a strong foundation in computer science.</p>
      <p>These courses helped me tackle advanced topics.</p>
      <p>Currently enrolled in the CSP class to further my skills.</p>
    </section>

    <section class="home-card" id="goals">
      <h2>Goals</h2>
      <p>Excited for new challenges in computer science.</p>
      <p>Stay tuned for updates on my progress and experiences.</p>
    </section>
  </div>

  <div class="home-actions">
    <div class="home-action-card">
      <p>Click the button below to login:</p>
      <a class="button button-primary" href="{{ site.baseurl }}/myjourney">Login</a>
    </div>

    <div class="home-action-card">
      <p>Click the button below to learn more about me:</p>
      <a class="button button-primary" href="{{ site.baseurl }}/about/">About Me</a>
      <p>Click here to access our Coding Languages Mini Project:</p>
      <a class="button button-secondary" href="https://cyberlord09.github.io/grouprepo_2025/">Coding Languages</a>
    </div>
  </div>
</section>