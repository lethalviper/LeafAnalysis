# LeafAnalysis
Digital Image Processing in Java for Collecting Data from Image of Leaves


About Project File -
package pixel
Leaf.java - contains code for collecting following information
            1. Average RGB values of Leaf
            2. Height to Width Ratio
            3. Increasing width measurements
            
            Analysis of above information is based on detection of green color pixels in image i.e. Leaf

            Leaf.java depends upon metadata-extractor-master packages and xmpcore 5.1.1
            
package test
ImageTest.java - contains main function
           Leaf Image file input is given as commandline agrument along with description of image.
           Collected information is saved in CSV file.

Uses - 
This project has many applications
                        1. Plant biodiversity analysis and survey
                        2. Plant nutrition and infection analysis based on coloration of Leaf
                        3. Leaf Age analysis
This code can be deployed in Android and live leaf analysis can be performed using camera

Problems -
         1. Identification of Plant based on Leaf is real Challenge
         2. Many leaf of different plant have same shape and coloration
         3. Coloration and shape of leaves of same plant varies. Please check Plots.docx
         
