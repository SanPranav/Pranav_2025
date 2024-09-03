---
layout: page
title: My Journey
permalink: /myjourney/
---
## Setting Up WSL and Ubuntu

Before I could clone the student repository, I needed to set up my development environment. Since I am using a Windows machine, I decided to use Windows Subsystem for Linux (WSL) along with Ubuntu. This setup provides a powerful and flexible environment for development.

### Installing WSL

To install WSL, I followed these steps:

1. **Enable WSL**: Open PowerShell as an administrator and run the following command:
   ```powershell
   wsl --install```

## Cloning the Student Repository

One of the first steps in my journey was to use the terminal to clone my own version of the student repository. This process involved opening the terminal, navigating to the desired directory, and running the `git clone` command with the repository URL.

By using the terminal, I was able to quickly and efficiently set up my local development environment, which allowed me to start working on my projects right away.

## Navigating the Terminal and Creating Directories

Having already taken CSSE 1 and CSSE 2, I was familiar with using the terminal, which made this process much easier. I used various commands to navigate and manage my directories:

- To create a new directory, I used the `mkdir` command:
  ```bash
  mkdir my_project```

  To list the contents of a directory, I used the ls command:
  ls

  To change directories, I used the cd command:
  cd

## Activating the Virtual Enviornment
To ensure that all dependencies are managed properly, I created and activated a virtual environment using the following commands:
```python3 -m venv venv
source venv/bin/activate```

## Setting Up Ruby and Installing Gems
For the project, I needed Ruby and several gems. I installed Ruby using a version manager and then installed the required gems:

# Install Ruby using a version manager like rbenv or rvm
```rbenv install 2.7.2
rbenv global 2.7.2```

# Install bundler and other necessary gems
```gem install bundler
bundle install```

By setting up the virtual environment and Ruby, I ensured that my development environment was isolated and had all the necessary dependencies. This setup allowed me to focus on coding without worrying about compatibility issues.