function [out, polyFeatures] = mapFeature(X1, degree)
% MAPFEATURE Feature mapping function to polynomial features
%
%   MAPFEATURE(X1, X2) maps the two input features
%   to quadratic features used in the regularization exercise.
%
%   Returns a new feature array with more features, comprising of 
%   X1, X2, X1.^2, X2.^2, X1*X2, X1*X2.^2, etc..
%
%   Inputs X1, X2 must be the same size
%
polyFeatures = ['Intercept'];
out = ones(size(X1(:,1)));
for i = 1:degree
    out(:, end+1) = (X1.^(i));
    polyFeatures=[polyFeatures;strcat('X1^',num2str(i))];
    %out(:, end+1) = (exp(X1.^(i)));
end
%out(:, end+1) = (log(X1));
%out(:, end+1) = (exp(X1));
%out(:, end+1) = (sin(X1));
for j = 2:0
        out(:, end+1) = X1.^(1/j);
        polyFeatures=[polyFeatures;strcat('X1^1/',num2str(j))];
end
end