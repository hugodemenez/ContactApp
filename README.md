
<h1 style='font-weight:bold;font-size:46px;'>Contact app developed in Java by :</h1>

<a href = "https://github.com/hugodemenez/ContactApp/graphs/contributors">
  <img src = "https://contrib.rocks/image?repo=hugodemenez/ContactApp" alt="contributors : Hugo DEMENEZ, Alban DUHAMEL, Quentin DUMESGE"/>
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
  - [Filter](#filter)
  - [Search](#search)
- [Demo App](#demo-app)
- [Encountered issues](#encountered-issues)
  - [Check boxes](#check-boxes)
  - [Lists of contacts](#lists-of-contacts)
  - [Custom pictures](#custom-pictures)
  - [Toast](#toast)

***

# How we started

## BrainStorming

We started by looking at the requirements.
We made a list of what we had to do and what could be done to go further.


## Figma Design

Then we used Figma, which is a user interface design software.
We designed the interface and found out where to put elements.

<a href = "/assets/figmaDesign.png">
  <img src ="/assets/figmaDesign.png"  alt="Figma Interface Design"/>
</a>


## Setting up the GitHub

Then we started the work.
We created a github repository and used sample project we had done during our practical work in the Java Course.

We had to set up multiple branches to work together at the same time on different functionnalities.

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
To achieve this we used the following library : https://github.com/mangstadt/ez-vcard
Don't hesitate to give a star to the project, since it helped us a lot to export our data into vCard files.

We simply brought the dependency through the maven pom file.

## Dynamic profile picture depending on gender
The image picture inside the contact description is linked to the gender.

In face we had another thing in minde : [Custom pictures](#custom-pictures)

## Filter

We decided to add some filter functionality. You can add a filter when you create / update a contact. You can provide a custom filter or use the filter already assigned to contacts inside the database. You

## Search

You can search by name, surname, nickname, phone number or email adress through the search field.

# Demo App 
<a href = "/assets/DemoApp.gif">
  <img src ="/assets/DemoApp.gif" alt="Demo Gif"/>
</a>

# Encountered issues

## Check boxes

We wanted to be able to select multiple persons to delete them at one time. 
Unfortunately, it didn't work because we already had the event listener on the click on a row of the table view.

## Lists of contacts

We had in mind lists of contacts like favorites, friends, family, work and so on...
But since everything has to be listed inside a database, we didn't manage to figure out how to deal with multiple lists.

## Custom pictures

We wanted to link a custom picture to the contact, however since we didn't manage to access files outside the project, like /Users/
we didn't want to create issues. We also tried to use images from the web but it was in BufferImage format and we didn't manage to cast them into javafx.Image class.

## Toast

Since we worked on an Android project, we dealt with toast and its implementation was very straight forward. However, there is no toast implementation in JavaFX. That's why we searched for a library which could provide some toast functionality.
We found out that a community member from stackoverflow had done something similar inside a toast class : https://stackoverflow.com/questions/26792812/android-toast-equivalent-in-javafx

We took his class and modified it in order to fit our needs. It is quite functional and beautiful as we planned.

