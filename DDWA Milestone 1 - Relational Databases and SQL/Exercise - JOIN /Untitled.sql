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