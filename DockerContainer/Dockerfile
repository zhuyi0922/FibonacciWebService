
FROM java:8
FROM openjdk:11

# 设置工作目录
WORKDIR /app

# 复制应用程序JAR文件到容器
COPY target/your-application.jar /app/your-application.jar

# 设置启动命令
CMD ["java", "-jar", "your-application.jar", "server", "config.yml"]
