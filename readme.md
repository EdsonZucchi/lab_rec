# Reserva de quartos
## Trabalho de recuperação

**_Require_**:
- IDE : *Intellij*
- JAVA version: *17*
- Project manager : *Maven*

**_Compile_**:

cd C:\Users\edson\Repositorios\lab_rec

dir /s /b src\main\java\*.java > class.txt

javac -d out @class.txt

**_Execute_**:

Client:
cd out
start rmiregistry
java io.github.edsonzuchi.client.HotelServer
java io.github.edsonzuchi.client.HotelClient

Sequence:

rmdir /s /q "C:\Users\edson\Repositorios\lab_rec\out"
cd C:\Users\edson\Repositorios\lab_rec
javac -d out @class.txt
cd out
start rmiregistry
java io.github.edsonzuchi.server.HotelServer


cd C:\Users\edson\Repositorios\lab_rec\out
java io.github.edsonzuchi.client.HotelClient


**_referencia_**:

https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/hello/hello-world.html