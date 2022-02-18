# SUPERRRRDUKE User Guide
SUPERRRRDUKE is an extension of the basic duke which can be used to store all tasks for users.

## Features 

1. Adding a task which might be a todo, an event or a deadline.
2. Marking the task as done or undone.
3. Deleting a task.
4. Sorting the tasks by nearest deadline.
5. Finding a task(s). 
6. Listing all tasks.
7. Automatically storing the tasks at every session into a file on the hard disk.
8. Automatically retrieving the tasks stored on startup.

## Usage

### Adding a todo: `todo {description}`

Adds a todo with the description. 

Example of usage: 

`todo Homework`

Expected outcome:

A todo with description homework will be added.

```
Got it. I've added this task:
[T][ ] Homework
Now you have 1 tasks in your list.
```

### Add an event: `event {description} /at {time}`

Adds an event with the description that happens at time.

Example of usage: 

`event return book /at 2019/09/15 1800`

Expected outcome:

An event with description return book and time of 2019/09/15 1800 will be added.

```
Got it. I've added this task:
[E][ ] return book (at: Sep 15 2019 1800)
Now you have 1 tasks in your list.
```

### Add a deadline: `deadline {description} /by {deadline}`

Adds a deadline with the description to be done by deadline.

Example of usage: 

`deadline return book /by 2019/10/15 1800`

Expected outcome:

A deadline with description return book and deadline of 2019/09/15 1800 will be added.


```
Got it. I've added this task:
[D][ ] return book (by: Sep 15 2019 1800)
Now you have 1 tasks in your list.
```

### Mark a task as done: `mark {task number}`

Marks the task with the task number as done.

Example of usage: 

`mark 1`

Expected outcome:

Marks task 1 (todo Homework) as done.

```
Nice! I've marked this task as done: 
[T][X] Homework
```

### Mark a task as undone: `unmark {task number}`

Unmarks the task with the task number as undone.

Example of usage: 

`unmark 1`

Expected outcome:

Marks task 1 (todo Homework) as undone.

```
OK, I've marked this task as not done yet:
[T][ ] Homework
```

### Delete a task: `delete {task number}`

Deletes the task with the task number.

Example of usage: 

`delete 1`

Expected outcome:

Deletes task 1 (todo Homework) as undone.

```
Noted. I've removed this task: 
[T][ ] Homework
```

### Sort tasks by deadline: `sort`

Sort tasks by deadline and lists them out.

Example of usage: 

`sort`

Expected outcome:

Sort tasks by deadline and lists them out.

```
Here are the tasks in your list:
1. [E][ ] return book (at: Sep 15 2019 1800)
2. [D][ ] return book (by: Sep 15 2019 1800)
```

### Find a task(s): `find {keyword to find}` 

Find task(s) based on keyword.

Example of usage: 

`find book`

Expected outcome:

Finds and lists all tasks with the word book in the description.

```
Here are the matching tasks in your list:
1. [E][ ] return book (at: Sep 15 2019 1800)
2. [D][ ] return book (by: Sep 15 2019 1800)
```

### List all tasks: `list` 

List all tasks.

Example of usage: 

`list`

Expected outcome:

Description of the outcome.

```
Here are the tasks in your list:
1. [T][ ] Homework
```


