# :video_game: JellyPin
**Jelly Pin is an Android mobile application made with Kotlin, where you can buy E-PINs.**

<p align="center"><img src="/screenshots/header.png" width="300"></p>


## Table of Contents
- [Features](#features)
- [Important Notes](#important-notes)
- [Pages](#pages)
  - [Splash Screen](#splash-screen)
  - [Login and Register](#login-and-register)
  - [Homepage](#homepage)
  - [Campaigns](#campaigns)
  - [Profile](#profile)
  - [Cart](#cart)

## Features

- Supports 2 languages: English and Turkish.
- Made with Retrofit, Glide, ViewPager2, Picasso Color Palette and Motion Layout based on MVVM architecture (ViewModel, LiveData)
- Dark Mode
- Toolbar Cart icon changes when the cart is empty or if there is an item in it.

## Important Notes
Some features that are there but not implemented with the database because the database was shared between other students:

- Users can level up by earning JP (Jelly Points) with 10% of the price of each item.
- Campaigns are launched according to certain user levels, for example, a campaign can only be launched for Level 5 and above users.
- If you are logged in with an admin account, you can see two buttons added to the homepage. But currently account types are not implemented. I have selected an account to be the admin.


## Pages

### Splash Screen
- Splash screen made with Motion Layout.
<p align="center"><img src="/screenshots/gif_splash_light.gif" width="200">&nbsp;&nbsp;<img src="/screenshots/gif_splash_dark.gif" width="200"></p>

## Login and Register
- If the conditions are not met, error message for each TextInputLayout appears on the register screen.

<p align="center"><img src="/screenshots/light_login.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/light_register.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/dark_login.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/dark_register.jpeg" width="200"></p>

### Homepage
- On the top side you can see the campaigns are moving on their own, its made with ViewPager2. You can click on a campaign to go to its details page.
- On the first screenshot you can see the homepage in the dark mode. 
- Second one shows how you can navigate between the games.
- Third one is to show how you can see the items of the selected game. Background of the opened menu is getting its color from the game's picture. I have used Picasso's Color Palette.
<p align="center"><img src="/screenshots/dark_homepage.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/gif_homepage.gif" width="200">&nbsp;&nbsp;<img src="/screenshots/gif_homepage_openmenu.gif" width="200">&nbsp;&nbsp;<img src="/screenshots/gif_homepage_menu.gif" width="200"></p>

### Campaigns

- On the campaign details page, the user can see if they are eligable to join the campaign.

<p align="center"><img src="/screenshots/light_campaign.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/dark_campaign.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/light_campaign_details.jpeg" width="200"></p>

### Profile

- There is an info button which the user can see their points. From top to bottom they are: How many different games have they bought items from, how many times have they gifted other people and how many JP do they have.
- There is an experience bar for the user to see how close they are to the next level.
- I have added a black bar in order to protect the user's privacy for the screenshots.

<p align="center"><img src="/screenshots/light_profile.jpeg" width="200">&nbsp;&nbsp;<img src="/screenshots/dark_profile.jpeg" width="200"></p>

### Cart

- Cart page's title changes according to the number of items at the cart.

<p align="center"><img src="/screenshots/gif_cart.gif" width="200">&nbsp;&nbsp;<img src="/screenshots/dark_cart.jpeg" width="200"></p>

### Admin Screen

- There are two buttons, one is for adding new items and the other one is to see all the items and set/unset them for sale.

<p align="center"><img src="/screenshots/yonetıcı.jpeg" width="200"></p>
