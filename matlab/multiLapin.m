function out = multiLapin(filePath)

list_dir=dir(strcat(filePath,'/*.txt'));

%list_dir={list_dir.name}
out=[];
for i = 1:size(list_dir)
  y=list_dir(i,1).name;
  x=strcat('data/',y);
  out =[out; lapin(x)];
end

end