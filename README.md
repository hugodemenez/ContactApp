
<h1 style='font-weight:bold;font-size:46px;'>Contact app developped in Java by :</h1>

<a href = "https://github.com/hugodemenez/ContactApp/graphs/contributors">
  <img src = "https://contrib.rocks/image?repo=hugodemenez/ContactApp"/>
</a>

<br>

***

<h1>Table of content </h1>


- [How we started](#how-we-started)
  - [BrainStorming](#brainstorming)
  - [Figma Design](#figma-design)
  - [Setting up the GitHub](#setting-up-the-github)
- [Functionalities](#functionalities)
  - [List all persons in the database](#list-all-persons-in-the-database)
  - [Add a new person with a form](#add-a-new-person-with-a-form)
  - [Update the data of existing persons](#update-the-data-of-existing-persons)
  - [Delete a person](#delete-a-person)
  - [Export functionality that takes all the data in the database and store it in a vCard](#export-functionality-that-takes-all-the-data-in-the-database-and-store-it-in-a-vcard)
  - [Dynamic profile picture depending on gender](#dynamic-profile-picture-depending-on-gender)
- [Demo App](#demo-app)

***

# How we started

## BrainStorming

We started by looking at the requirements.
We made a list of what we had to do and what could be done to go further.


## Figma Design

Then we used Figma, which is a user interface design software.
We designed the interface and found out where to put elements.

<a href = "/assets/figmaDesign.png">
  <img src ="/assets/figmaDesign.png"/>
</a>


## Setting up the GitHub

Then we started the work.
We created a github repository and used sample project we had in our practical work during the Java Course.

We had to set up multiple branches to work together at the same time on differents functionnalities.

It helped us to debug our own code, without dealing with other branches errors.


# Functionalities
## List all persons in the database
The main page reads all persons in the database and fills a TableView with the necessary information


## Add a new person with a form
We can create a new person in the database through a form. We have to fill every field in order to create a proper Contact instance.

## Update the data of existing persons
We can select an element of the TableView and update its content.

## Delete a person
When we select an element of the TableView we arrive on the editing view, we are able to delete the person through this view
## Export functionality that takes all the data in the database and store it in a vCard
## Dynamic profile picture depending on gender


# Demo App 
<a href = "/assets/DemoApp.gif">
  <img src ="/assets/DemoApp.gif"/>
</a>