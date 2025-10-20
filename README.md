# Introduction

**Nighthawk Pages** is a project designed to support students in their Computer Science and Software Engineering education. It offers a wide range of resources including tech talks, code examples, and educational blogs.

GitHub Pages can be customized by the blogger to support computer science learnings as the student works through the pathway of using JavaScript, Python/Flask, and Java/Spring.

---

## Student Requirements

Del Norte HS students will be required to review their personal GitHub Pages at each midterm and final. This review will contain a compilation of personal work performed within each significant grading period.

In general, Students and Teachers are expected to use GitHub Pages to build lessons, complete classroom hacks, perform work on JavaScript games, and serve as a frontend to full-stack applications.

Exchange of information could be:

1. sharing a file: `wget "raw-link.ipynb"`
2. creating a template from this repository
3. sharing a fork among team members
4. etc.

---

## History

This project is in its 3rd revision (aka 3.0).

The project was initially based on Fastpages. But this project has diverged from those roots into an independent entity. The decision to separate from Fastpages was influenced by the deprecation of Fastpages by authors. It is believed by our community that the authors of Fastpages turned toward Quatro. After that change of direction, Fastpages did not align with the Teacher's goals and student needs. The Nighthawk Pages project has more of a raw development blogging need.

### License

The Apache license has its roots in Fastpages. Thus, it carries its license forward. However, most of the code is likely unrecognizable from those roots.

### Key Features

* **Code Examples**: Provides practical coding examples in JavaScript, including a platformer game, and frontend code for user databases using Python and Java backends.
* **Educational Blogs**: Offers instructional content on various topics such as developer tools setup, deployment on AWS, SQL databases, machine learning, and data structures. It utilizes Jupyter Notebooks for interactive lessons and coding challenges.
* **Tools and Integrations**: Features GitHub actions for blog publishing, Utterances for blog commenting, local development support via Makefile and scripts, and styling with the Minima Theme and SASS. It also includes a new integration with GitHub Projects and Issues.

### Contributions

* **Notable Contributions**: Highlights significant contributions to the project, including theme development, search and tagging functionality, GitHub API integration, and the incorporation of GitHub Projects into GitHub Pages. Contributors such as Tirth Thakker, Mirza Beg, and Toby Ledder have played crucial roles in these developments.

* **Blog Contributions**: Often students contribute articles and blogs to this project. Their names are typically listed in the front matter of their contributing post.

---

## GitHub Pages Setup

The absolutes in setup up...

### **Activate GitHub Pages Actions**

This step involves enabling GitHub Pages Actions for your project. By doing so, your project will be automatically deployed using GitHub Pages Actions, ensuring that your project is always up to date with the latest changes you push to your repository.

* On the GitHub website for the repository go to the menu: **Settings → Pages → Build**
* Under the Deployment location on the page, select **"GitHub Actions"**.

### **Update `_config.yml`**

Modify the `_config.yml` file to reflect your repository's name. This configuration ensures that your project's styling is correctly applied, making your deployed site look as intended rather than unstyled or broken.

```text
github_repo: "Pranav_2025" 
baseurl: "/Pranav_2025"
```

### **Set Repository Name in Makefile**

Adjust the `REPO_NAME` variable in your Makefile to match your GitHub repository's name. This facilitates automatic updating of posts and notebooks on your local development server.

```make
# Configuration, override port with usage: make PORT=4200
PORT ?= 4100
REPO_NAME ?= Pranav_2025
LOG_FILE = /tmp/jekyll$(PORT).log
```

---

## Tool Requirements

All `GitHub Pages` websites are managed on GitHub infrastructure and use GitHub version control. Each time you change files in GitHub, it initiates a GitHub Action that rebuilds and publishes the site with Jekyll.

* GitHub uses [**Jekyll**](https://jekyllrb.com/) to transform your markdown and HTML content into static websites and blogs.
* A Linux shell is required to work with this project. Ubuntu is preferred, though macOS shell is supported as well.
* Visual Studio Code is the Nighthawk Pages author's preferred editor. It has many extensions that simplify working with GitHub, GitHub Pages, and multiple programming languages.
* The README includes details on the file structure, metadata tagging, and styling conventions.

---

## Development Environment Setup

A comprehensive setup guide is available [here](https://nighthawkcoders.github.io/portfolio_2025/devops/tools/home).

### Quick Start

Run these commands to obtain the project, then locate into the project directory, install tools, and make.

```bash
git clone https://github.com/nighthawkcoders/Pranav_2025.git
cd Pranav_2025/scripts
```

#### Windows WSL and/or Ubuntu Users

```bash
./activate_ubuntu.sh
```

#### macOS Users

```bash
./activate_macos.sh
```

#### Kasm Cloud Desktop Users

```bash
./activate.sh
```

---

## Run Server on Localhost

To preview the project locally, you will use `make` commands.

### Bundle Install

The first time you clone and run the project, execute:

```bash
bundle install
```

### Start the Server

```bash
make
```

After running, you should see a terminal output like:

```text
http://0.0.0.0:4100/Pranav_2025/
```

Click the address to open it in your browser.

### Regeneration of Web Application

Anytime you save a `.ipynb` or `.md` file, Jekyll regenerates automatically:

```text
Regenerating: 1 file(s) changed at 2025-10-20 06:54:32
    _notebooks/2025-10-20-example.ipynb
```

### Other `make` Commands

#### Stop Server

```bash
make stop
```

#### Clean Environment

```bash
make clean
```

#### Observe Build Errors

```bash
make convert
```

---

## Development Support

### File Naming Rules

* For markdown in `_posts`:

  *  `2025-08-02-First-Day.md`
* For notebooks in `_notebooks`:

  *  `2025-08-02-First-Day.ipynb`

### Tags

Use categories for organization in front matter:

```yaml
categories: [Tools]
```

### Search

Use `search_exclude: true` in front matter to hide pages from search results.

### Navigation Bar

Control menus and their order via `_config.yml`.

### Blog Page

Use this front matter for image and description:

```yaml
image: /images/sample.jpg
description: "Intro to my project"
```

To hide a post from the blog page:

```yaml
hide: true
```

---

## SASS Support

Nighthawk Pages supports multiple themes layered on top of **Minima**.
Modify `_sass/minima/custom-styles.scss` to select your theme.

Search for “GitHub Pages Minima” or “GitHub Pages Themes” for more info.

---

## Includes

Reusable components are imported via **liquid syntax**.
Example:

```liquid
{%- include post_list.html -%}
```

---

## Layouts

To create or use a custom layout, make an HTML file in `_layouts`, then set in front matter:

```yaml
layout: mylayout
```

---

## Metadata (Front Matter)

Front matter provides metadata for `.md` and `.ipynb` files.

Example:

```yaml
---
toc: true
comments: true
layout: post
title: Jupyter Python Sample
description: Example Blog!!!  This shows code and notes from hacks.
type: ccc
courses: { csa: {week: 5} }
---
```

This metadata:

* Controls where the post appears
* Defines type (`ccc` = Code, Code, Code)
* Places it under a specific course and week

Would you like me to also format it with **a custom introduction header** (like “Pranav’s Nighthawk Pages”) and a **short bio section** at the top (e.g., explaining your focus or portfolio goals)? It would make the README feel more personal while staying professional.
