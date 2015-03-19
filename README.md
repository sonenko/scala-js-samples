Basically project to play / compare ScalaJS vs pure Javascript.

## 1. compile examples
Open terminal. and execute:
```bash
cd {project root}
sbt
root/fastOptJS
```
 It will generate javascript from scala code and put it to 
`{project_dir}/root/target/scala-2.11/root-fastopt.js`.

## 2. see result
Open `{project root}/web/index.html` in browser and play with app(press buttons)

## 3. run tests
```bash
sbt
test
```
