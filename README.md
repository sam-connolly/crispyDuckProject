KF5012 Software Engineering Practice
Computer science project brief

Introduction.
This is the brief for the group project assessment for KF5012 Software Engineering Practice. This is the brief for students on the “Computer Science” course. 
This document explains the scenario for the problem you are trying to solve, and provides a number of “missions” that you will complete. When you create your team’s project proposal, you will need to choose which missions your team will be attempting, and identify which team member is responsible for which mission(s).
To fully understand this brief you will need to also read the Assessment Specification (also known as the “umbrella assessment”). That document lays out general principles, how the group-work works, and the marking schemes for the different types of mission. 
The task
This document discusses a problem that a fictional company want to solve with a software solution. You should analyse the needs of the company, design a solution, implement the solution prototype and test it.
Technology
It is expected that you will use Java to implement the solution. Other languages are only permitted with the prior approval of the module tutors. 

Capytec task allocation system
Capytec plc is a company that provides a range of business and light industrial services. They are based at a large site just north of Nottingham. The site has a number of buildings of various sizes. The site is maintained by a team of six caretakers. During a working day, they carry out a various maintenance and cleaning tasks. Some of these tasks are routine (e.g. emptying wastebaskets). Some of these are one-off tasks (e.g. replacing a broken window). 
The current system is paper-based. Each day, administrators in the Property Services department provide a list of one-off tasks that need completing. At the start of each day, the caretakers read the list of one-off tasks that was produced the previous day. The caretakers also maintain their own list of routine tasks. The caretakers distribute tasks among themselves. They cross off any one-off tasks that have been allocated. If there are too many tasks to complete in one day, unallocated tasks are left for the next day. Usually, there are not many one-off tasks, so the caretakers also select some routine tasks to do that day.
The caretakers are experienced enough to be able to select appropriate tasks. For example, they can see for themselves whether the windows need cleaning and have a good idea of when the waste bins will need emptying. 
The company want to move to an electronic system for maintaining the list of tasks and distributing them amongst the caretakers.
Your team has been asked to produce a working prototype that offers four main features:
Maintain a list of tasks.
Support a team of caretakers in allocating daily tasks amongst themselves. 
Maintain a record of when certain tasks have been completed and who completed them.
Provide various reports on task completion and allocation to interested parties.

I mainly want to minimise the time spent by the caretakers discussing who will do each task. It used to only take a few minutes at the start of the day to decide who will do what, but now they seem to spend the first half an hour wrangling over the jobs. I’d like a program that assigns them tasks quickly so they can get on with things. I’d also like to be able to see who did what each day – for about the last month or so.
The caretakers also need to be able to see what jobs they are supposed to be doing. Sometimes they forget and start discussing it all over again.
    --Ed, manager

We need to spend a lot of time deciding who will do each job. It’s not easy, as there are lots of things to consider. There are some jobs no-one likes and it’s unfair for the same person to do them every day – we like to share those jobs around. But there are also some jobs that particular people are good at or don’t mind doing. For example, Mike is good at cleaning the windows and he doesn’t mind doing it. He can do it much quicker than I can so it makes sense for him to do them. We really need to decide each day, though. There are all sorts of special reasons that we might want to allow for. Last week, David’s knees were hurting, so we didn’t want him to go up and down the stairs too much and instead gave him the jobs on the ground floor. Livia often gets chest infections, so we don’t ask her to work outside when it is raining. Sometimes we just don’t feel like doing a particular job. We can sort all these problems out at the start of the day, and we all then know what we have to do for that day.
    -- Anita, caretaker

The team clearly need to work more efficiently, but it’s also important for them to feel motivated. If they need to change the plan every day, then that’s the way it has to be. It’s also important for things to be fair. They all need to be doing about the same amount of work, but to feel that they have the freedom to assign the work amongst themselves.
    -- Jonathan, Human resources

There’s one member of the team – I won’t say who – that just doesn’t pull his weight. Or her weight. Could be a her, of course. I’m not saying. We spend ages arguing over who does what, but he just argues and argues so we end up just agreeing to do tasks that he refuses to do. 
Even so, I still think it’s better than the managers telling us what to do. They don’t have a clue. Take polishing the floors, for example. They say it takes two hours. Well, it depends on who’s doing it. It might take me two hours, but Livia can do a really great job in half the time, and it looks better than if I did it. We can all save ourselves a lot of time by being clever about who does what. If the managers just give us jobs, then it will all take a lot longer and won’t get done as well.
-- Mike, caretaker

At the moment, the team keep forgetting to sign off jobs. For some jobs, we need to be able to prove that they were done. Things like cleaning the toilets and replacing the water tanks in the water coolers. If they don’t get signed off and checked, we could get in trouble from the health and safety people. Other jobs don’t need this – you can tell whether the waste bins have been emptied just by looking. I’d like to be able to know whether important jobs have been completed and when – preferably as soon as they are finished, rather than waiting for the next day.
    -- Sue, property services

The caretakers have an office on the first floor. There is only space in there for one PC, so they will need to share that computer. The door is locked, and the program won’t have any sensitive data on it, so it doesn’t need to be very secure. You’ll still need to find some way to record who is using the PC, otherwise the wrong person will sign off a job. They are not used to using computers every day, so the program should be easy to use to avoid them making mistakes.
    -- Tawfik, IT services

Missions
Your task splits up into ten “missions”. Two of these missions will be shared by the group. (It is up to you which two.) The other eight missions should be divided between you. (Two missions each.)
If you are working as a team of three, then you do not need to complete missions 9 and 10.
Project management 
Requirements analysis 
Software analysis 
Testing 

Task entry component 
Data component 
Task allocation component 
User account component

Task logging (for teams of four only)
Reporting component (for teams of four only)


Some advice from the CTO:
The client has come to you with a problem. It is your job to come up with a solution. Don’t expect them to tell you what to make. You can solve the problem in various ways – with an interface that allows collaborative planning, using an algorithm to assign tasks, or something else. There is no single right way to do this. Whatever approach you take, it is your job to offer a possible solution to their problem. If they don’t like it, then we can discuss changes with them later. Right now, we want a fully-working system that is your suggested approach to solving their problem.
You are creating a Working Prototype. The word “prototype” means many things. In this case, it is a system that is fully functional and shows what a suggested system can do. If the client likes the prototype, we can turn it into the final system, so don’t use the fact that it is a prototype as an excuse for sloppy programming or incomplete features. Excuses make us look bad.
The prototype will be a stand-alone system. You can assume that all interested parties will use the same computer, so there is no need to worry about networking or concurrency.
In your planning, think about the order in which things will be done. The data component probably needs to be done early. You will do most of the testing and bug-fixing near the end. Take this into account when selecting missions – you don’t want one person to be doing two missions that are near the end of the project, unless he/she is happy to plan his other duties around that.

Missions
There are ten missions. The team should select two missions to be shared by the team as a joint responsibility.  Team members will also have individual responsibility for two other missions.
Teams of three should ignore missions 9 and 10.

Mission 1
Project management
The Project Lead will take control of the project management aspects of the project. It will be their responsibility to facilitate all other roles to do their jobs. They will need to help the team decide on the scope of the project and clearly define the nature of the solution to be produced. Alongside the other roles they will create a breakdown of tasks to be completed, and determine prioritisation of these tasks, and dependencies (e.g. Fred cannot do X until Julie does Y). They then need to turn these tasks into a reasonable and agreed schedule, which takes into account other commitments (e.g. other assignments), giving all roles clear tasks and deadlines. As the project progresses, the project lead will need to adapt the schedule as necessary, knowing which extra tasks can be slotted in if work goes well, and which tasks are easiest to drop if work goes badly. The project lead needs to keep a record of meetings, decisions and agreements made, and have a clear picture of progress. If need be, they will be responsible for settling disputes, handling contingency for unforeseen problems and anything else that affects the effective working of the team. 
The Project Lead does not have the power to hire and fire, so to perform the role effectively they will need to not take a dictatorial approach, (unless this is what the team as a whole agrees to). They are reliant on the co-operation of the rest of the team in order to be effective. A team with any sense will realise that co-operation with their Project Lead is a good route to maximising marks. It is suggested that early on in the project, the team should discuss their aspirations in terms of marks for this work, as this will help the Producer schedule and assign work appropriately.
Special note: Like all other missions, this can be shared by the group. It is possible to rotate the project lead responsibility, or to adopt a collaborative management approach. You will be expected to explain and justify your strategy if you take this approach.


The Project Lead will produce the following project management elements
Team Code of Conduct
Project Proposal (due end of week 4)
Skills audit of the team (due end of week 4)        
Risk analysis
Work breakdown, task prioritisation and task allocation
Schedule with agreed deadlines, including initial version and ongoing revisions
Ongoing logs of progress, problems and solutions.
Records of communication pertaining to decisions by the group.


The qualities that need to be displayed in this documentation are:
Clarity
Completeness
Fair and ethical treatment of team members.


There are several ways in which a Project Lead could go beyond expectations, which mostly consist of using extra techniques to assist the production process, e.g. applying SCRUM sprints, using PERT analysis to determine the critical path, or make reference to deeper learning and advice on project management.
To show novelty or innovation a Project Lead will need to develop a significant novel and beneficial process to assist the development process.
The Project Lead will give instruction to all other roles.


Mission 2
Requirements analysis
The requirements analyst is responsible for maintaining the requirements model documentation. It is expected that he/she will use UML use cases. The requirements model should evolve as the program develops, so that at the end of the project it is an accurate statement of the system functionality. Your model should be complete, accurate and well-organised.t
The elements that are needed are:
A statement of the purpose of the system and how it meets the client’s needs.
A UML Use Case Diagram.
A set of Use Case Descriptions, showing the prime scenario and user-level exceptions and alternatives.


The requirements analysis should exhibit the following qualities:
Completeness – The model should show all functionality of the system from the users’ perspectives.
Internal consistency – The model should be internally consistent. The Use Case Descriptions should be consistent with the Use Case Diagram, and should be consistent with each other in cases where they are linked.
Consistency with prototype – The model should be maintained so that it remains consistent with the solution under development. At the end of the project, the model should be consistent with the final prototype.
Well-organised – It should be easy to find and identify specific requirements.


The requirements analyst can go beyond expectations by providing a requirements model that is detailed, specific and includes a thorough consideration of exceptions.

To show novelty or innovation, the requirements analyst can adopt and communicate an innovative approach to solving the client’s problem.



Mission 3
Software analysis
The software analyst is responsible for producing documentation that describes the structure of the program and explains how it works. This documentation will support anyone who will be responsible for maintaining the program and is useful in organising the implementation and testing of the code. Your documentation should be an accurate and complete map of the final system. It is expected that you will use UML notation.
The elements that are needed are:
A class diagram showing the contents and relationships of classes and interfaces.
State machine diagrams for any classes that make use of interesting state behaviour.
Sequence diagrams and/or activity diagrams to show the dynamic behaviour of the system. These do not need to be done for each use case, only to show the behaviour of the system in situations where object interaction is not trivial.

The software analysis should exhibit the following qualities:
Accurate use of UML notation. The diagrams should make use of correct UML notation as taught on the degree course.
Consistency. The diagrams should be consistent with each other.
Accuracy. The diagrams should correctly describe the behaviour of the system.
Completeness. The diagrams should convey a full understanding of the static structure and dynamic behaviour of the system.


To go beyond expectations the analyst can make use of further UML diagrams such as package diagrams, component diagrams or activity diagrams to convey key information, or may make use of advanced notation.

To show novelty or innovation the analyst can make use of UML to communicate complex information not supported by basic notation taught on the programme.





Mission 4
Testing
The “Tester” will provide a support service to all other roles, providing independent and rigorous testing approach to the ongoing project. As this role will probably have more activity late in the project, it may be sensible for this role to be taken by the same person as the Project Lead role (which is more front-loaded).
The Tester will receive instructions from the other roles as they progress with work, and may be asked to test the workings of code, the functionality of systems, the accessibility of the environment, and if relevant the logic, functionality, balance and engagement of the gameplay. Their job is to “destruction test”: to try relevant permutations and combinations and to find, highlight and document flaws and failings in the project. It is not their job to fix these problems, but to report clearly back to whoever does have the responsibility for these areas.
The Tester is required to formulate a process whereby they log the testing requirements, devise test cases, perform the tests and report back to whoever asked for the testing.
The elements that the Tester needs to produce are
A process for testing and reporting on testing
A full record of the test process in use.


The qualities that the Tester needs to show in their work are:
Clarity of process
Clear thinking in test cases
Clear communication of test results
Timeliness in performing testing.

To go beyond expectations for the tester does not mean increasing the number and scope of tests. Rather, they could apply structured testing approaches like Boundary Value Analysis to improve the robustness of testing. Any significant application of effective testing techniques, found by researching the topic, could be considered in this category.
To show novelty or innovation the Tester will need to originate a new, effective and beneficial approach to testing. 
The Tester will be instructed by all other roles as appropriate, and their role is to assist the creative roles to perform effectively.

Mission 5
Task entry component
The developer should design and implement a method for users to manage a list of tasks that need to be completed. 
The component should include the following elements:
Functionality to allow an appropriate user to add new regular and one-off tasks.
Functionality to allow the user to add relevant information about tasks, such as the type of task, duration, importance and frequency.
Safeguards against common errors, such as unnecessary duplicate tasks or tasks.
Functionality to present a sortable lists of existing tasks.
Functionality to allow the user to select and edit task information.

The system should exhibit the following qualities: 
User friendly – The system should be easy to use for a new user with basic computer literacy. It should not take excessive time to carry out tasks.
Reliability – The system should run correctly without crashing or hanging, regardless of user actions.
Maintainability – The program code should be well-structured, documented and easy to maintain.
Aesthetic – The system is not customer-facing, so does not need to be elegant or exciting, but should adopt a neat, consistent layout.


The developer can go beyond expectations by offering advanced usability features such as user guidance, undo features and filtering.

The developer can show novelty and innovation by adopting an interesting approach to user interaction that makes the process faster, easier, more reliable or that otherwise advances the needs of the client. Alternatively, the developer can adopt an innovative approach to code design that offers benefits to either the developer or the client.


Mission 6
Data component
The data component stores persistent information using an appropriate method. You could use text files, a database or XML files. The component responsible for loading and saving the data safely and providing a mechanism for other components to easily and securely access the data.
The data analyst/programmer will discuss the needs of the rest of the team and generate a data model, which is likely to need ongoing refinement as the system is developed.
The component should include the following elements:
Documentation of the data model, such as an Entity Relationship Diagram or other appropriate tool.
A method for persistent storage of data.
Appropriate methods that allow other components to query and update the data.

The system should exhibit the following qualities: 
Efficiency – The data structure should avoid duplicate or unnecessary data (for example, it should be normalised to third normal form if using a relational database model)
Maintainability – The program code should be well-structured, documented and easy to maintain.
Interoperability – The data access features should be easy for other components to use.


The data analyst/programmer can go beyond expectations by adding robust data security, by allowing for concurrency or by implementing a reusable/flexible software package.

The data analyst/programmer can show novelty and innovation by making good use of advanced database features, such as views, triggers and complex constraints. Alternatively, the developer could make use of novel database approaches such as NoSQL or NewSQL. It is also possible to show innovation by taking a highly generic approach to the package design.



Mission 7
Task allocation component
– It is your job to design and implement a method to allocate daily tasks, either automatically or by supporting the caretakers as they collaboratively assign tasks. 
The component should include the following elements:
Functionality to allow assign tasks to caretakers allowing for expected time taken, preferences, individual talents and special conditions.
Functionality to provide a clear statement of daily tasks to each caretaker.
Functionality to provide a sortable list of tasks, clearly indicating tasks that have not been assigned, as well as other key information such as task priority.
Safeguards to prevent common errors, such as the same task being assigned twice.

The system should exhibit the following qualities: 
User friendly – The system should be easy to use for a new user with basic computer literacy. It should not take excessive time to carry out tasks.
Reliability – The system should run correctly without crashing or hanging, regardless of user actions.
Maintainability – The program code should be well-structured, documented and easy to maintain.
Aesthetic – The system is not customer-facing, so does not need to be elegant or exciting, but should adopt a neat, consistent layout.


The developer can go beyond expectations by offering advanced usability features such as user guidance, undo features and filtering.

The developer can show novelty and innovation by adopting an interesting approach to user interaction that makes the process faster, easier, more reliable or that otherwise advances the needs of the client. Alternatively, the developer can adopt an innovative approach to code design that offers benefits to either the developer or the client.

Mission 8
User account component
This component allows users to sign in to the system and access features relevant to them. The system does not need to be very secure, but should make sure that users have access to the right features and cannot abuse features that should not be available to them. For example, caretakers should not be able to add new users, and human resources staff do not clean the toilets. When recording tasks done, the program needs to log the current user against that task.
The component should include the following elements:
Functionality to provide a way for users to log on, or otherwise securely indicate their identity.
Functionality for adding new users to the system.
Functionality for removing users from the system.
Functionality for editing user information.
Functionality for categorisation of users with appropriate access features.
Software interfaces that provide information to other components when they need to know the identity of the current user.
Functionality to allow users to update security information such as passwords.

The system should exhibit the following qualities: 
User friendly – The system should be easy to use for a new user with basic computer literacy. It should not take excessive time to carry out tasks.
Security – The system should guard against malicious or accidental use of unauthorised system features.
Maintainability – The program code should be well-structured, documented and easy to maintain.
Aesthetic – The system is not customer-facing, so does not need to be elegant or exciting, but should adopt a neat, consistent layout.

The developer can go beyond expectations by offering advanced usability features such as user guidance, undo features and filtering.

The developer can show novelty and innovation by adopting an interesting approach to user interaction that makes the process faster, easier, more reliable or that otherwise advances the needs of the client. Alternatively, the developer can make use of encryption or other security techniques without impeding the usability of the system.



Mission 9
Task logging component
This component allows caretakers to log tasks that they have completed and which require logging. 
Certain tasks (both regular and one-off) need to be logged as completed for various reasons, such as health & safety monitoring, performance management, etc. It is important that the time of completion is noted. In some cases, tasks have deadlines. In most cases, tasks can be logged by the caretaker that completes the task. In a small number of cases, tasks need to be signed off by another caretaker or a manager.
The component should include the following elements:
Functionality to allow caretakers to log the completion of certain regular and one-off tasks, including key information such as who completed the task, the time and additional comments.
Functionality allow caretakers to easily find tasks assigned to them, either completed or pending. This allows caretakers to quickly log task completion and identify tasks that are close to the deadline (or past the deadline.)
Functionality to allow caretakers and managers to edit the record of tasks completed.


The system should exhibit the following qualities: 
User friendly – The system should be easy to use for a new user with basic computer literacy. It should not take excessive time to carry out tasks.
Reliability – The system should run correctly without crashing or hanging, regardless of user actions.
Maintainability – The program code should be well-structured, documented and easy to maintain.
Aesthetic – The system is not customer-facing, so does not need to be elegant or exciting, but should adopt a neat, consistent layout.


The developer can go beyond expectations by offering advanced usability features such as user guidance, undo features and filtering.

The developer can show novelty and innovation by adopting an interesting approach to user interaction that makes the process faster, easier, more reliable or that otherwise advances the needs of the client. Alternatively, the developer can adopt an innovative approach to code design that offers benefits to either the developer or the client.

Mission 10
Reporting component
This component should present reports that are needed by the caretakers, manager and property services. The information should be useful, timely, complete, accurate and easy to understand. The exact needs of the client are not fully formed, so the developer should provide example reports, but allow for future modification.
The company have not requested a specific format – paper / on-screen / email / dashboard. It is up to the developer to suggest a reporting process and demonstrate its effectiveness.
The component should include the following elements:
Functionality to provide reports on the current status of tasks, particularly overdue tasks.
Functionality to provide reports on the historic performance of the caretakers, particularly signs of poor performance or unequal workload.
Functionality to provide reports on the historic completion of tasks, particularly tasks that are often overdue or not completed satisfactorily.

The system should exhibit the following qualities: 
User friendly – The system should be easy to use for a new user with basic computer literacy. It should be easy for managers to find the relevant information in the reports.
Reliability – The system should run correctly without crashing or hanging, regardless of user actions.
Maintainability – The program code should be well-structured, documented and easy to maintain.
Aesthetic – The system is not customer-facing, so does not need to be elegant or exciting, but should adopt a neat, consistent layout.

The developer can go beyond expectations by offering advanced usability features such as user guidance, complex presentation of statistics and filtering.

The developer can show novelty and innovation by adopting an interesting approach to data presentation that makes the process faster, more informative, more reliable or that otherwise advances the needs of the client. 


