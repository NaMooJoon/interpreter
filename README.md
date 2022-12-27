## Implement RBMRCFAE

**HGU - JunHyun Kim - 21800179**

**RBMRCFAE** is a parser and interpeter supporing...

- R: call by reference
- BM: box and variable
- RC: recursive function
- F: function
- AE: plus, substitution and multiply
- (L: with laziness)
<br>


- ### Environment

  This is implemented by Java & Gradle. Here's my environment.

  ```bash
  >> java --version
  openjdk 11.0.12 2021-07-20 LTS
  OpenJDK Runtime Environment Corretto-11.0.12.7.2 (build 11.0.12+7-LTS)
  OpenJDK 64-Bit Server VM Corretto-11.0.12.7.2 (build 11.0.12+7-LTS, mixed mode)
  ```

  ```bash
  >> gradle --version
  
  ------------------------------------------------------------
  Gradle 7.4.2
  ------------------------------------------------------------
  
  Build time:   2022-03-31 15:25:29 UTC
  Revision:     540473b8118064efcc264694cbcaa4b677f61041
  
  Kotlin:       1.5.31
  Groovy:       3.0.9
  Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
  JVM:          11.0.12 (Amazon.com Inc. 11.0.12+7-LTS)
  OS:           Mac OS X 12.0.1 x86_64
  ```
  <br>
  
- ### Build

  - #### Gradle

    ```bash
    >> gradle build
    
    BUILD SUCCESSFUL in 4s
    ```

    ````bash
    gradle run --args="<options>"
    > Task :run
    App running start!!
    	< result >
    ````

  - #### Options

    ````bash
     -c,--concrete <concrete code>   The option that passes the argument for a
                                     concrete code
     -h,--help                       Help
     -l,--laziness                   The option that enables laziness
     -p,--parser                     The option that only enables a parser
    ````

    - Must required option
      : -c 
    - The other options
      : -h, -l, -p
  <br>


- ### Example

  - #### Input example

    ```bash
    gradle run --args="-c '{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} a}}}}'"
    ```

  - #### Output example

    ```bash
    > Task :run
    App running start!!
    (v*s (numV 20) (aSto 3 (numV 10) (aSto 2 (numV 20) (aSto 4 (numV 10) (aSto 3 (numV 20) (aSto 2 (numV 10) (aSto 1 (refclosV x (refun y (app (fun z (seqn (setvar x (id y)) (setvar y (id z)))) (id x))) (mtSub)) (mtSto))))))))
    ```
<br>


### BNF of Concrete Code

```tex
<RBMRCFAE> ::= <num>
						| {+ <RBMRCFAE> <RBMRCFAE>}
						| {- <RBMRCFAE> <RBMRCFAE>}
						| <id>
						| {fun {<id>} <RBMRCFAE>}
						| {refun {<id>} <RBMRCFAE>}
						| {setvar <id> <RBMRCFAE>}
						| {newbox <RBMRCFAE>}
						| {openbox <RBMRCFAE>}
						| {seqn <RBMRCFAE> <RBMRCFAE>}
						| {if0 <RBMRCFAE> <RBMRCFAE> <RBMRCFAE>}
						| {rec <id> <RBMRCFAE> <RBMRCFAE>}
```
- #### Example code

  ```java
  // This is just an example code.
  '{with {x 3} {with {f {fun {y} {+ x y}}} {with {x 5} {f 4}}}}';
  '{with {fn {fun {x} {+ x x}}} {fn 6}}';
  '{with {x 3} 3}';
  '{with {x 3} {+ x 3}}';
  '{{fun {x} 0} {+ 1 {fun {y} 2}}}'; // only laziness
  '{{fun {x} 0} {+ 1 3}}';
  '{{fun {x} {+ {+ x x} {+ x x}}} {- {+ 4 5} {+ 8 9}}}';
  '{with {x 3} {with {f {fun {y} {+ x y}}} {with {x 5} {f 4}}}}';
  '{rec {count {fun {n} {if0 n 0 {+ 1 {count {- n 1}}}}}} {count 9}}';
  '{rec {fac {fun {n} {if0 n 1 {* n {fac {- n 1}}}}}} {fac 9}}';
  
  '{with {q {newbox 10}} {openbox q}}';
  '{with {b {newbox 7}} {with {c {setbox b 10}} {openbox b}}}'; // laziness
  '{with {b {newbox 7}} {seqn {setbox b 10} {openbox b}}}';
  '{with {x 3} {seqn {setvar x 5} {setvar x 6}}}';
  '{with {a 3} {seqn {{fun {x} {setvar x 5}} a} a}}';
  '{with {a 3} {seqn {{refun {x} {setvar x 5}} a} a}}';
  
  '{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} b}}}}';
  '{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} a}}}}';
  
  ```

  You can run the one of the codes above by passing argument option ('-c').
  
  

<br>


### RBMRCFAE with Laziness

- #### Result

  The result of RBMRCFAE with Laziness ('-l' option) can be different with the RBMRCFE without Laziness.
  For example,

  ```java
  '{{fun {x} 0} {+ 1 {fun {y} 2}}}'; // only laziness
  ```

  This code cannot be executed by the WITHOUT laziness version.
  Since the expression '{fun {y} 2}' is not a value.

  However, at the laziness' point of view, the no matter what is the x's value, the result should be zero.
  In laziness version, it doesn't calculate the expression if {+ 1 {fun {y} 2}}.
  That's why it doesn't occur the error.

  

  Then, what about this case?

  ```java
  '{with {b {newbox 7}} {with {c {setbox b 10}} {openbox b}}}'; // laziness
  ```

  In this case, the result of WITHOUT the laziness version is `(numV 10)` and the result of WITH the laziness version is `(numV 7)`.

  The different reason is the laziness.

- #### Store Structure