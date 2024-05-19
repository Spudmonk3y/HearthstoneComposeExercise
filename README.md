# HearthstoneComposeExercise

###This is a Technical Exercise repository on making simple calls to a free API and displaying the results in Jetpack Compose.

The API chosen for this exercise is an API purporting to have scraped all the cards for the game Hearthstone by Blizzard Entertainment.

It provided several easily split up "chunks" of data in the form of the player's class and the set the cards were released in, as well as a rudimentary search functionality

Unfortunately it become apparent after I had been working with it for a bit that there is a lot of junk data coming from them.
To assist with finding filled out data I added an image icon indicator as well as colored the card set icon based on their rarity, while having an image does not guarantee that a card is fully populated, a card with a colored icon and an image is a decent indicator that it is at least somewhat populated
The icon library I was utilizing for set icons had not been updated in some time, and as such you will notice several of the newer sets just us the classic icon.

In addition to the app I have written a very small number of Unit and Instrumented tests, as well as a rudimentary workflow that runs the linter and tests, and if they pass builds and deploys a binary to firebase.
If Interested, you should be able to download the .apk file directly from https://appdistribution.firebase.dev/i/a90d3dfc225739f2