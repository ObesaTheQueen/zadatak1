import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
var RestService = /** @class */ (function () {
    function RestService(http) {
        this.http = http;
    }
    RestService.prototype.getCarsSmall = function () {
        return this.http.get('../data/cars-small.json')
            .subscribe(function (res) { return res; });
    };
    RestService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], RestService);
    return RestService;
}());
export { RestService };
//# sourceMappingURL=rest.service.js.map