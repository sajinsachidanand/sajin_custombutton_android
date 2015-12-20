var app = angular.module('tegApp', []);

//Controllers
app.controller('mainController', ['$scope', '$log', function($scope, $log) {
    console.log("inside...");
    $scope.addRow = function(){
      $scope.templateURL = 'directives/textbuttoninput.html';
 }; 
    
    
}]);

app.controller('textButtonInput', ['$scope', '$log', function($scope, $log) {
    
    
}]);

app.controller('textTwoButtonInput', ['$scope', '$log', function($scope, $log) {
    
    
    
}]);



//Directives
app.directive("textButtonInput", function() {
   return {
       restrict: 'E',
       templateUrl: 'directives/textbuttoninput.html',
       replace: true
   }
});

app.directive("textTwoButtonInput", function() {
   return {
       restrict: 'E',
       templateUrl: 'directives/texttwobuttoninput.html',
       replace: true
   }
});