clear ; close all; clc

fprintf('Loading data ...\n');

%% Load Data
data = load('test.txt');
X = data(:, 1);
y = data(:, 2);
m = length(y);
plotData(X, y);

%Add the polynomial colummns to the data, the first value is always 1 (the thetha0)
degree=3;
X=mapFeature(X,degree);

% Initialize fitting parameters
initial_theta = zeros(size(X,2), 1);

% Set regularization parameter lambda to 1 (you should vary this)
lambda = 0;

% Set Options
options = optimset('GradObj', 'on', 'MaxIter', 400000);

% Optimize
[theta, J, exit_flag] = ...
	fminunc(@(t)(costFunction(t, X, y, lambda)), initial_theta, options);

  % Display  result
fprintf('Theta computed : \n');
fprintf(' %f  ', theta);
fprintf('\n');
fprintf('Std Error : \n');
fprintf(' %f \n', J);

% Plot the linear fit
hold on; % keep previous plot visible
plot(X(:,2), X*theta, '-')
legend('Training data', 'Polinomial regression')
hold off % don't overlay any more plots on this figure

%pp = interp1(data(:, 1),y,'pchip','pp');
%[breaks,coefs,L,order,dim] =unmkpp(pp)
