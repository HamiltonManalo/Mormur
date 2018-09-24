# Mormur
A educational platform to connect experts to learners

* It runs
* It can be pinged
* It does require some leg work to set up
* It doesn't return data from the API yet

# Dev Guide
*Credentials for logging into our database are stored in the files. Please treat this as a vulnerability until its changed*

## Source Control
Your parents have probably never spoken to you about the vital need to practice strong source control. Your friends may say "**MERGE TO MAIN** man, its cool"
Please don't. Whenever you begin work on a new feature or patch, start a new branch. In order to have great source control you need to learn to use git. Or you can download a handly git application that will let you do a ton, without having to touch the terminal. [Please check out](https://www.sourcetreeapp.com) 
Its my git application of choice and it makes managing your source significantly easier. 

### SERVER 
The server is run on a Spring.io boot platform that brings in a good number of packages automatically. It allows ORM (Object Relational Mapping) to the Database via *Hibernate* which is included as part of the *Spring-boot-starter-jpa* package. Hibernate generates SQL code, allows pure SQL queries and maps SQL queries to objects which can then be manipulated as part of Java code. 

### FRONTEND 
The front end is run on React/ReactDOM. React allows you to create elegant SPA's (Single Page Applications) and uses *jsx*. *jsx* is a markup language combining JavaScript and HTML. Typically developers lean towards a separation of concerns, i.e. your JS goes in a file and your html goes in a file; however with React you can combine both the logic and the design for a web page in the same file which can make it easier to follow and design. 
#### Topics to know
* Promises - Async programming in JS
  * These are used in JS to handle network requests
* ReactJS


### BUILD PROCESS
Currently the project is built through the *Maven* build tool. What does a build tool do? 
* Downloads and installs Dependencies (Read: It brings in code libraries, installs programs such as node.js and runs them to automate the build process
* Builds the React webpages. React combines all of its code into one .js file at the end of the build process, while in our codebase it will be split up by files. It uses Webpack to do this. The entire build process can be daunting, fortunately Maven takes a lot of this off our hands and automates it 
* In order to build the application and run it you will need to use a command line (Powershell or CMD on windows, terminal on OSX/Linux/Unix). If you are using windows you need to add Maven to your path. 
   * Once added to your path you can execute a build by running this command in the directory containing your *mormur.pom* file "mvn spring-boot:run" if you make changes to JS and need a full rebuild please use the "mvn clean spring-boot:run" command 
   * After running the command you should see a message like

            `<2018-06-23 17:47:11.257 INFO 24116 --- [ main] brunel.mormur.mormurMain : Started mormurMain in 7.239 seconds (JVM running for 57.778)>`
   *This means your server is now running and you can reach the home page by launching your browser to "Localhost:8080"*          
   
                  

 ### TOOLING
 * Rapid Environmental Editor: [Download Here](https://www.rapidee.com/en/download) allows you to edit your system path in a much cleaner way. 
 * IDEA IDE [Download Here](https://www.jetbrains.com/) Jetbrains is normally a hefty subscription fee. While were in school its free. 
 * Source Tree GIT UI [Download Here](https://www.sourcetreeapp.com) Handy UI for GIT. Doesn't have all the features of the command line but stream lines review commits and most common use cases

