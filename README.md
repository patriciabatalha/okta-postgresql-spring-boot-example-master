# Spring Boot with PostgreSQL, Flyway, and JSONB

This example shows how to create an application using Spring Boot, PostgreSQL, Flyway, and JSONB. Please read [Spring Boot with PostgreSQL, Flyway, and JSONB](https://developer.okta.com/blog/2019/02/20/spring-boot-with-postgresql-flyway-jsonb) to see how this app was created.

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-postgresql-spring-boot-example.git
cd okta-postgresql-spring-boot-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start the app, run:
 
```bash
./gradlew bootRun
```

This will likely fail. You need to configure a PostgreSQL database with the following settings in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://192.168.99.100:5432/coursedb
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create
```

And start PostgreSQL in a Docker container:

```bash
docker pull postgres:11
docker run --name dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres:11
# CREATE db coursedb
docker exec dev-postgres psql -U postgres -c"CREATE DATABASE coursedb" postgres
```

### Create an Application in Okta

You will need to create an application in Okta to get your values to lock down your API. 

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don’t have an account) and navigate to **Applications** > **Add Application** > **Service**. This will allow you to create a resource server that does not have a login page or any way to obtain new tokens.

Click **Next**, type the name of your service, then click **Done**. Copy and paste the _Client ID_ in to your `application.properties` file. Your issuer URL can be found in **API** > **Authorization Servers**.

```properties
okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
okta.oauth2.clientId={yourClientId}
```

**NOTE:** The value of `{yourOktaDomain}` should be something like `dev-123456.okta.com`. Make sure you don't include `-admin` in the value!

After modifying this file, restart your app and you should be able to authenticate with Okta.

## Links

This example uses [Okta's Spring Boot Starter](https://github.com/okta/okta-spring-boot).

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2019/02/20/spring-boot-with-postgresql-flyway-jsonb), or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).
