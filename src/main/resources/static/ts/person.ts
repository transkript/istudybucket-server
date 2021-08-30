export class Person  {
    name: string;
    age: number;
    gender: string;

    constructor(name: string, age: number, gender: string) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    show() {
        console.log(`${this.name} is ${this.age} years old and of gender ${this.gender}`);
    }
}