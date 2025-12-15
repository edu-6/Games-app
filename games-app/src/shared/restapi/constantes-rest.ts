export class ConstantesRest {
    public readonly API_URL = 'http://localhost:8080/rest-api-games/api/v1/';

    public getApiURL (): string {
        return this.API_URL;
    }
}