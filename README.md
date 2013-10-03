Scraper
=======

A Selenium based WebPage Scraper written in Java. 

---------------
Requirements:
---------------
Firefox . If you want to change source code, then you will have to download the latest Selenium JAVA API. 
If you just want to test, provided is a stand-alone jar executable file  <strong>Scraper.jar </strong>. Execution method is described in Description section.

---------------
Tested on: 
---------------
ubuntu 12.04

---------------
Description:
---------------
This project uses the Selenium API to fetch webpage and carries out required search and list operations. It addresses all the corner cases like invalid page number, out of bound page number. It handles some of the frequent Selenium Webdriver exceptions like NoSuchElementException and replays. 
There are hardcoded latencies to allow a  webpage to load fully. Owing to this, the program loads slow. 

** Please be patient and give it a minute or two to finish at max!  ** The program replays itself in case of exception, causing some minor latency.

There are 2 use cases:

a. java -jar Scraper.jar <keyword> (e.g. java -jar Scraper.jar "digital camera")
Sample output: Number of Search results for query digital camera  : 2706

b. java -jar Scraper.jar <keyword> <page number> (e.g. java -jar Scraper.jar "digital camera" 20)

---------------
Sample output: 
---------------

Number of Search results for query digital camera  : 2706
Accessing Page Number 20
modelName = Fujifilm Instax Mini 7S Instant Camera (includes Fujifilm Mini Film 10pk)
Price = $59.00

modelName = Canon EOS 60D Black 18MP Digital SLR with 18-135IS Lens, EF 70-300mm Lens and 16GB SD Card
Price = $1,349.00

modelName = Nikon D800 Black 36.3MP DSLR Camera, 3.2
Price = $2,799.88

modelName = FUJIFILM S8200 Digital Camera with 16 Megapixels and 40x Optical Zoom
Price = $259.00

modelName = Canon EOS 60D DSLR Camera Body with EF 70-300mm Lens and 16GB Memory Card
Price = $1,057.00

modelName = Canon EOS 60D Black 18MP Digital SLR (Body + Lens) with EF-S 55-250mm Zoom Lens and 16GB SD Card
Price = $1,298.00

modelName = Nikon D5100 16.2MP Black DSLR Camera with Nikkor 18-200mm Zoom Lens and 16GB Memory Card
Price = $1,198.00

modelName = Sony SLT-A57K Black 16.1MP Alpha DSLR Camera, 27-82.5mm Zoom Lens, 3.0
Pricing info not available for this model. In Store only. 

modelName = Nikon D3100 14.2MP DSLR Camera with 18-55mm VR Lens and Nikkor AF-S 55-300mm Zoom Lens Value Bundle
Price = $698.00

modelName = Nikon D3100 14.2MP DSLR Camera with Nikon Nikkor 18-200mm Zoom Lens and 16GB Memory Card
Price = $1,048.00

modelName = Polaroid XVF-720LC Blue Waterproof 720p HD Digital Camcorder with 2
Price = $29.88

modelName = Canon Red PowerShot SX280 Digital Camera with 12.1 Megapixels and 20x Optical Zoom
Price = $279.88

modelName = Cobra Digital CBD-DCA1670-SILVER 16MPX Dual View Digital Waterproof Cam
Price = $71.97

modelName = Pentax K-r 12.4MP DSLR Camera Bundle w/ 18-55mm Lens, 3
Price = $499.00

modelName = Pentax K-r 12.4MP DSLR Camera Bundle w/ 18-55mm Lens, 3
Price = $599.00

modelName = Olympus Black VR-370 Digital Camera with 16 Megapixels and 12x Optical Zoom
Price = $139.00

---------------
Future Work:
---------------
Get rid of the firefox GUI, make it headless using virtual frameworks like xvfb. Handle the remaining selenium exceptions and remote cases if any left.

