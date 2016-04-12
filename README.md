# GrabMyWaifu
## If you want the binary installer for Windows, the link is here [Splitmane.com](http://splitmane.com) under the downloads
GrabMyWaifu is a simple application that allows for the mass downloading of media by thread on 4Chan.  It currently is set-up to download all of the media of a single thread into a created folder in the name of that thread.  The backbone of this application is JavaFX with the parsing done by jSoup.  Currently, the only availible native installer is for Windows.  

### What it does-

  * Downloads all available media from a thread on 4Chan.
  * Very simple user interface.
  * Thread by thread sorting based on given name on 4Chan.
  * Downloads all file formats (gif, png, jpg, webm, etc).


### What you need to be careful of- 
  * No filter in current implementation (it is editable to specify resolution, size, and format. None are used in current design.
  * THIS HAS NO FILTER, it will not track your downloads, but that doesn't mean your ISP can't and won't.  Your downloads are up to you.Be safe and smart.
  * No bandwidth monitor/cap.  This will take everything you can handle and try it.  It's meant for those who are not worried about data caps due to the amount of files it can, and will download.
  
### Best Practices- 
 * Only use it on threads that are "safe".
 * Be weary of your amount downloaded and your bandwidth used.
 * Since it puts downloads in a specified thread folder, I recommend using one folder as a master archive, with sub folders that are created by the application.
  
### Upcoming Changes-
 * Downloading preferences (file format, tag choices, website choices, resolution size.).
 * Import-able preferences using xml files for website tag parsing.
 * Native installers for Mac OSX and Linux operating systems.
  
### How-
 * GMW uses jSoup to parse tags of a given url, that page is then searched for particular tags, then the tags are taken into account, the media link is extracted, and the media downloaded.  jSoup has the potential to specify any set of tags, and therefore will (in theory) work on almost every website that hosts images.  The tricky part is getting the tags correct when it comes to downloading full sized images and not the directly linked thumbnails.
  

