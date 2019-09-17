# User Guide

## List tasks
Displays all tasks in the order that they were entered.

### Usage
> list

#### Example of usage: 
```
list
```

#### Expected outcome:
If there are no tasks in the list:
```
You have no tasks in the list.
```

If there are tasks in the list:
```
1. [T][✓] read book
2. [D][✗] borrow book (by: 21 Sep 2019 12:30 PM)
3. [E][✗] CCA meeting (at: 22 Sep 2019 09:20 AM)
```

## Add a task

### `todo` - Todo Task
Adds a Todo to the task list.

### Usage
> todo _desc_

<code><i>desc</i></code> - Task description

#### Example of usage: 
 ```
 todo join sports club
 ```

#### Expected outcome:
```
Got it. I've added this task:
  [T][✗] join sports club
Now you have 4 tasks in the list.
```

---

### `deadline` - Deadline Task
Adds a Deadline to the task list.

### Usage
> deadline [_desc_] --by [_deadline_]

<code><i>desc</i></code> - Task description  
<code><i>deadline</i></code> - Task deadline  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Have to be after tag `--by`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Have to be in format `dd/MM/yyyy HHmm`

#### Example of usage:
```
deadline borrow book --by 21/09/2019 1200
```

#### Expected outcome:
```
Got it. I've added this task:
    [D][✗] borrow book (by: 21 Sep 2019 12:00 PM)
Now you have 5 tasks in the list.
```

---

### `event` - Event Task
Adds an Event to the task list.

### Usage
> event [_desc_] --at [_event time_]

<code><i>desc</i></code> - Task description  
<code><i>event time</i></code> - Event time  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Have to be after tag `--at`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Have to be in format `dd/MM/yyyy HHmm`

#### Example of usage:
Valid attributes provided.
```
event project meeting --at 22/09/2019 0930
```

#### Expected outcome:
```
Got it. I've added this task:
    [E][✗] project meeting (at: 22 Sep 2019 09:30 AM)
Now you have 6 tasks in the list.
```

## Find tasks
Search and displays tasks where task description meet the keyword provided.

### Usage
> find [_keyword_]

<code><i>keyword</i></code> - Keyword used to find tasks

#### Example of usage: 
```
find book
```

#### Expected outcome:
```
Here are the matching tasks in your list:
1. [T][✓] read book
2. [D][✗] borrow book (by: 21 Sep 2019 12:30 PM)
```

## Mark tasks as done
Mark tasks as done based on task numbers provided.

### Usage
> done [_task no..._]

<code><i>task no...</i></code> - 1 or more task numbers

#### Example of usage: 
```
done 2 3
```

#### Expected outcome:
```
Nice! I've marked this task as done:
    [D][✓] borrow book (by: 21 Sep 2019 12:30 PM)
    [E][✓] CCA meeting (at: 22 Sep 2019 09:20 AM)
```

## Delete tasks
Delete tasks based on task numbers provided.

### Usage
> delete [_task no..._]

<code><i>task no...</i></code> - 1 or more task numbers

#### Example of usage: 
```
delete 2 3
```

#### Expected outcome:
```
Noted. I've removed these tasks:
    [D][✓] borrow book (by: 21 Sep 2019 12:30 PM)
    [E][✓] CCA meeting (at: 22 Sep 2019 09:20 AM)
Now you have 1 tasks in the list.
```

## Update tasks
Update task attributes.

### Usage
> update [_task no_]  {--desc [_desc_]} {--by [_deadline_]} {--at [_event time_]}

Attribute | Dscription
---|---
<code><i>task no</i></code> | Task number to update  
<code><i>desc</i></code>* | Task description to update  <br> Have to be after tag `--desc`   
<code><i>deadline</i></code>* | Deadline to update <br> Only for Deadline tasks <br> Have to be after tag `--by` <br> Have to be in format `dd/MM/yyyy HHmm`  
<code><i>event time</i></code>* | Event time to update <br> Only for Event tasks <br> Have to be after tag `--at` <br> Have to be in format `dd/MM/yyyy HHmm`

*Only require at least 1 attribute

#### Example of usage: 
```
update 2 --desc Borrow book --by 22/09/2019 1300
```

#### Expected outcome:
```
Got it. I've updated this task:
    [D][✓] Borrow book (by: 22 Sep 2019 01:00 PM)
```

## Exit application

### Usage
> bye

#### Example of usage: 
```
bye
```

#### Expected outcome:
```
Bye. Hope to see you again soon!
```