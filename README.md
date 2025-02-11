# Mormur
A educational platform to connect experts to learners

* It runs! 
* You can ask Questions!
* It looks prettier

# Dev Guide

##Setting up a new development environment
Congratulations on joining the mormur team, you are likely a new developer and have never in your entire course, faced a task quite so monumental as setting up a project in a modern development environment. <br>
Luckily, this guide is here to help you through that process and if you follow it you shouldn't have a hard time getting developing! So please read carefully and start here! <br> <br>
1. Download Intelli J ultimate edition from [JetBrains](www.jetbrains.com). With a student account you get the ultimate edition for free and its an amazing, modern IDE.<br> 

2. So you have your IDE installed? Great, jump to the bottom of the page and grab SourceTree, Cmder, Rapid Environment Editor and NodeJS. At minimum you need NodeJS to run the project, the other applications just make life easier.<br> 

3. Once you've hooked up your preferred version control - whether that is VCS in Intelli J, Commandline Git, or SourceTree pull the repository to your PC

4. In your commandline of choice navigate to your Mormur folder. The team has typically stored it in a root folder of the drive, IE C:/Mormur or D:/Mormur and then run `npm update` followed by `npm run-script watch webpack.config.js`

5. Open up Intelli J and select the Mormor folder from your chosen location and import the project as a "Gradle" project. Once it starts opening you should see a bar at the bottom of the screen showing dependencies downloading. You're almost done! 

6. Intelli J will ask you a few questions once it finishes loading data and you should probably answer yes! "Do you want annotation processing?" yes! 

You should now be ready to start coding! check our trello board and jump on a story! 

####Some new dev tips! 
* Pull from github everyday and merge master into your branch. On SourceTree this can be achieved by switching to master and pulling, then hop back onto your branch, right click on master and choose to "Merge master onto current branch" 
* Always start a new branch before you start coding! We do not accept pull requests from master. ever. seriously. 
* Stackoverflow has EVERYTHING they didn't teach you in school.

### Source Control
Your parents have probably never spoken to you about the vital need to practice strong source control. Your friends may say "**MERGE TO MAIN** man, its cool"
Please don't. Whenever you begin work on a new feature or patch, start a new branch. In order to have great source control you need to learn to use git. Or you can download a handly git application that will let you do a ton, without having to touch the terminal. [Please check out](https://www.sourcetreeapp.com) 
Its my git application of choice and it makes managing your source significantly easier. 

### SERVER 
The server is run on a Spring.io boot platform that brings in a good number of packages automatically. It allows ORM (Object Relational Mapping) to the Database via *Hibernate* which is included as part of the *Spring-boot-starter-jpa* package. Hibernate generates SQL code, allows pure SQL queries and maps SQL queries to objects which can then be manipulated as part of Java code. 

### FRONTEND 
The front end is run on React/ReactDOM/React-Router. React allows you to create elegant SPA's (Single Page Applications) and uses *jsx*. *jsx* is a markup language combining JavaScript and HTML. Typically developers lean towards a separation of concerns, i.e. your JS goes in a file and your html goes in a file; however with React you can combine both the logic and the design for a web page in the same file which can make it easier to follow and design. 
#### Patterns
* Components - Each "view" is broken up into components ie; the header is a component that is built once and used on each page. Each content page such as Q & A Sessions is also a component. Components are either a root piecce of html, or a combination of other components used to compose more complex designs. The ideal is that anything that can be reused should be made into a component so that it can be used anywhere. 

* Routing is used to load new content onto the page via React-Router. Any time a new "router"(url change) is made, an accompanying route should be added to the java "HomeController" so that users can "refresh" the page without getting a 404. 

#### React Development 
In order to transpile React into vanilla JS A webpack build needs to be run via NodeJS. Below are the instructions for getting it running after adding the project to your local environment. 
* you must have nodeJS installed 
1. Launch your command line and navigate to the mormur project directory <br>
`> cd D:/MyProjects/mormur` <br>
2. Run the update command to import react and other JavaScript packaged required for the frontend to run. <br>
`> npm update`
3. Run the npm-run script command <br>
`> npm run-script watch webpack.config.js` <br>
4. You should see the below run! this means rebuild on change is now running. any time you make a change to a react file in the js directory it will automatically update, just refresh your browser! <br>
` Hash: e4798bf537890f4602a0` <br>
`Version: webpack 1.15.0` <br>
`Time: 2508ms` <br>
`                                         Asset     Size  Chunks             Chunk Names` <br>
 `   ./src/main/resources/static/built/bundle.js  1.14 MB       0  [emitted]  main` <br>
`./src/main/resources/static/built/bundle.js.map  1.25 MB       0  [emitted]  main` <br>
 `  [0] multi main 40 bytes {0} [built]` <br>
    `+ 285 hidden modules` <br>
    Alternatively you may run the 'npx webpack' command to do a single run of the react build. 
    
#### Topics to know
* Promises - Async programming in JS
  * These are used in JS to handle network requests
* ReactJS
* Flex CSS 
* JPA for database access
* RESTful API's

### BUILD PROCESS
Currently the project is built through the *Gradle* build tool. What does a build tool do? 
* Downloads and installs Dependencies (Read: It brings in code libraries, installs programs such as node.js and runs them to automate the build process
* In order to build the application and run it you will need to use a command line (Powershell or CMD on windows, terminal on OSX/Linux/Unix). 
   * Once added to your path you can execute a build by running this command in the directory containing your *gradle.build* file "gradle bootRun" 
   * After running the command you should see a message like

  `<2018-06-23 17:47:11.257 INFO 24116 --- [ main] brunel.mormur.mormurMain : Started mormurMain in 7.239 seconds (JVM running for 57.778)>` <br>
   *This means your server is now running and you can reach the home page by launching your browser to "Localhost:8080"*          
   
 * In another terminal window you will need to run the npm watch command. <br>
 `npm run-script watch webpack.config.js` <br>
 This will cause node to rebuild the react files any time a change is made.

 ### TOOLING
 * Rapid Environmental Editor: [Download Here](https://www.rapidee.com/en/download) allows you to edit your system path in a much cleaner way. 
 * IDEA IDE [Download Here](https://www.jetbrains.com/) Jetbrains is normally a hefty subscription fee. While were in school its free. 
 * Source Tree GIT UI [Download Here](https://www.sourcetreeapp.com) Handy UI for GIT. Doesn't have all the features of the command line but stream lines review commits and most common use cases
 * CMDER A very nice Terminal Emulator [Download Here](http://cmder.net/) that features browser like tabs, hot keys and more! 
 * NodeJS is a backend JavaScript framework built on the V8 Engine (Chrome)[Download Here](http://www.nodejs.org/). It is required for building the React frontend of the project. 