function graph(theta, tMax, nbPoints)
close all; 

graf = linspace(0,tMax,nbPoints);
[graf,polyFeatures2]=mapFeature(graf',2);
theta=theta';
for i =1:size(theta,2)
  hold on;
  plot(graf(:,2), graf*theta(:,i), '-')
 
end

end