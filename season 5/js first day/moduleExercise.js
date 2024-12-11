 class Service {
    constructor(name) {
        this.name = [];
    }

    add (){
        this.name.push(Person)
    }

    remove (){
        this.clients.splice(id, 1);
    }

    destroy (){
        this.person = [];
    }

    update (){

    }

    get (id) {
    return this.person[id]
      }

   get list (){
    return this.Person
   } 
 }  

 const service = new Service();

 class Person{
    constructor(name, age){
        this.name = name;
        this.age = age;
    }
}

const person1 = new Person(1, "Manuel", 19);
const person2 = new Person(2, "Leandro", 21);
const person3 = new Person(3, "Filipe", 27);

service.add(person1);

console.log(service.list);


  