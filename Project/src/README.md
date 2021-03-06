Capytec plc is a company that provides a range of business and light industrial services. They are based at a large site just north of Nottingham. The site has a number of buildings of various sizes. The site is maintained by a team of six caretakers. During a working day, they carry out a various maintenance and cleaning tasks. Some of these tasks are routine (e.g. emptying wastebaskets). Some of these are one-off tasks (e.g. replacing a broken window). 
The current system is paper-based. Each day, administrators in the Property Services department provide a list of one-off tasks that need completing. At the start of each day, the caretakers read the list of one-off tasks that was produced the previous day. The caretakers also maintain their own list of routine tasks. The caretakers distribute tasks among themselves. They cross off any one-off tasks that have been allocated. If there are too many tasks to complete in one day, unallocated tasks are left for the next day. Usually, there are not many one-off tasks, so the caretakers also select some routine tasks to do that day.
The caretakers are experienced enough to be able to select appropriate tasks. For example, they can see for themselves whether the windows need cleaning and have a good idea of when the waste bins will need emptying. 
The company want to move to an electronic system for maintaining the list of tasks and distributing them amongst the caretakers.
Your team has been asked to produce a working prototype that offers four main features:
    1. Maintain a list of tasks.
    2. Support a team of caretakers in allocating daily tasks amongst themselves. 
    3. Maintain a record of when certain tasks have been completed and who completed them.
    4. Provide various reports on task completion and allocation to interested parties.

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



Some advice from the CTO:
The client has come to you with a problem. It is your job to come up with a solution. Don’t expect them to tell you what to make. You can solve the problem in various ways – with an interface that allows collaborative planning, using an algorithm to assign tasks, or something else. There is no single right way to do this. Whatever approach you take, it is your job to offer a possible solution to their problem. If they don’t like it, then we can discuss changes with them later. Right now, we want a fully-working system that is your suggested approach to solving their problem.
You are creating a Working Prototype. The word “prototype” means many things. In this case, it is a system that is fully functional and shows what a suggested system can do. If the client likes the prototype, we can turn it into the final system, so don’t use the fact that it is a prototype as an excuse for sloppy programming or incomplete features. Excuses make us look bad.
The prototype will be a stand-alone system. You can assume that all interested parties will use the same computer, so there is no need to worry about networking or concurrency.
In your planning, think about the order in which things will be done. The data component probably needs to be done early. You will do most of the testing and bug-fixing near the end. Take this into account when selecting missions – you don’t want one person to be doing two missions that are near the end of the project, unless he/she is happy to plan his other duties around that.
