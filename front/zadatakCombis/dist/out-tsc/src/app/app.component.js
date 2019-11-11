import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { RestService } from './shared/rest.service';
var AppComponent = /** @class */ (function () {
    function AppComponent(restService) {
        this.restService = restService;
        this.title = 'zadatakCombis';
    }
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.restService.getCarsSmall().then(function (cars) { return _this.cars1 = cars; });
        this.cols = [
            { field: 'vin', header: 'Vin', width: '25%' },
            { field: 'year', header: 'Year', width: '15%' },
            { field: 'brand', header: 'Brand', width: '35%' },
            { field: 'color', header: 'Color', width: '25%' }
        ];
    };
    AppComponent = tslib_1.__decorate([
        Component({
            selector: 'app-root',
            templateUrl: './app.component.html',
            styleUrls: ['./app.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [RestService])
    ], AppComponent);
    return AppComponent;
}());
export { AppComponent };
//# sourceMappingURL=app.component.js.map