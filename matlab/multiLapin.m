function out = multiLapin(filePath)
%Funtion that returns the coefs of multiple polynomial regressions of the data in the folder in filePath

close all; 
list_dir=dir(strcat(filePath,'/*.txt'));

%list_dir={list_dir.name}
out=[];
for i = 1:size(list_dir)
  y=list_dir(i,1).name;
  x=strcat('data/',y);
  [matrix, feat] = lapin(x);
  out =[out; matrix];
end

feat
end