---
layout: page
title: My Journey
permalink: /myjourney/
---
## Activating the Virtual Environment

To ensure that all dependencies are managed properly, I created and activated a virtual environment using the following commands:

<div class="code-block">
  <pre><code class="language-bash">python3 -m venv venv
source venv/bin/activate</code></pre>
</div>

## Setting Up Ruby and Installing Gems

For the project, I needed Ruby and several gems. I installed Ruby using a version manager and then installed the required gems:

### Install Ruby using a version manager like rbenv or rvm

<div class="code-block">
  <pre><code class="language-bash">rbenv install 2.7.2
rbenv global 2.7.2</code></pre>
</div>

### Install bundler and other necessary gems

<div class="code-block">
  <pre><code class="language-bash">gem install bundler
bundle install</code></pre>
</div>

By setting up the virtual environment and Ruby, I ensured that my development environment was isolated and had all the necessary dependencies. This setup allowed me to focus on coding without worrying about compatibility issues.

<style>
  .code-block {
    background-color: #f5f5f5;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    margin: 10px 0;
    animation: fadeIn 2s ease-in-out;
  }

  @keyframes fadeIn {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }

  .language-bash {
    color: #ffffff;
    font-family: 'Courier New', Courier, monospace;
  }
</style>