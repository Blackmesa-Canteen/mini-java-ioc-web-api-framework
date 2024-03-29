# mini-java-ioc-web-framework
 
# Intro

A hand crafted light-weighted backend framework for SWEN90007 assignment project. This is a frame work inspired by Spring Boot.

This Framework can help you to build up a RESTful web api server quickly.

![f80d23254e95a2de8fce7f23a3c0f62](https://user-images.githubusercontent.com/69796042/182006586-c59c743f-4e98-47a6-ad69-c372088228b8.jpg)


# Features

- Tomcat server embedded.
- IoC singleton bean container, class scan.
- RESTful api
- Annotation support for MVC: @Handler, @Blo, @Dao.
- JSR303 Validation for Json Body parameter.
- Filter for request, helpful in authorization.
- Demo Configuration file support.
- Demo CRUD template.

# demo
See api-server-example project in the repo.

# Open Source Projects

This project is inspired by the other project:

- [jsoncat](https://github.com/Snailclimb/jsoncat)
- [handwritten-mvc](https://github.com/tyshawnlee/handwritten-mvc)
- [spring-boot](https://github.com/spring-projects/spring-boot)
- [spring-imitator](https://github.com/Blackmesa-Canteen/spring-imitator)

# license
```
MIT License

Copyright (c) 2022 996Worker/Blackmesa-Canteen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
