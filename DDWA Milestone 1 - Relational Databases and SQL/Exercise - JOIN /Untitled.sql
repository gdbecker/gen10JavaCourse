use TrackIt;

select *
from TaskStatus
where IsResolved = 1;

select 
	Task.TaskId,
    Task.Title,
    TaskStatus.Name
from TaskStatus
inner join Task on TaskStatus.TaskStatusId = Task.TaskStatusId
where TaskStatus.IsResolved = 1;

select
	Project.Name,
    Worker.FirstName,
    Worker.LastName,
    Task.Title
from Project
inner join ProjectWorker on Project.ProjectId = ProjectWorker.ProjectId
inner join Worker on ProjectWorker.WorkerId = Worker.WorkerId
inner join Task on ProjectWorker.ProjectId = Task.ProjectId and ProjectWorker.WorkerId = Task.WorkerId
where Project.ProjectId = 'game-goodboy';

select *
from Task
left outer join TaskStatus on Task.TaskStatusId = TaskStatus.TaskStatusId;

select
    Task.TaskId,
    Task.Title,
    IFNULL(Task.TaskStatusId, 0) as TaskStatusId,
    IFNULL(TaskStatus.Name, '[None]') as StatusName
from Task
left outer join TaskStatus 
    on Task.TaskStatusId = TaskStatus.TaskStatusId;
    
select 
	Project.Name ProjectName -- An alias makes this more clear
from Project
left outer join ProjectWorker on Project.ProjectId = ProjectWorker.ProjectId
where ProjectWorker.WorkerId is null; -- throws out projects with workers

-- Workers without a project
select 
	Project.Name ProjectName,
    Worker.FirstName,
    Worker.LastName
from Project
right outer join ProjectWorker on Project.ProjectId = ProjectWorker.ProjectId
right outer join Worker on ProjectWorker.WorkerId = Worker.WorkerId
where ProjectWorker.ProjectId is null;

-- What's above can be written as below
-- Workers without a project
select
    Worker.FirstName,
    Worker.LastName
from ProjectWorker
right outer join Worker on ProjectWorker.WorkerId = Worker.WorkerId
where ProjectWorker.ProjectId is null;

-- Switching the RIGHT to LEFT
-- Use LEFT outer joins to better visualize
select
    Worker.FirstName,
    Worker.LastName
from Worker
left outer join ProjectWorker on Worker.WorkerId = ProjectWorker.WorkerId
where ProjectWorker.WorkerId is null;

select
	parent.TaskId ParentTaskId,
    child.TaskId ChildTaskId,
    concat(parent.Title, ': ', child.Title) Title
from Task parent
inner join Task child on parent.TaskId = child.ParentTaskId;

select
	concat(w.FirstName, ' ', w.LastName) WorkerName,
    p.Name ProjectName
from Worker w
cross join Project p
where w.WorkerId = 1
	and p.ProjectId not like 'game-%';