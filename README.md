## ABOUT
**Groups watcher** – VK bot for monitoring and analyzing posts on the wall of VK groups. It solves the problem of searching and adapting content for your own community using the vk api and LM Studio.
<br><br>


## KEY FEATURES
The key features of the bot are:
- monitoring of the community walls, search and save the content of new posts; 
- analyzing community wall content on specified topics, rewriting it in other words for personal use;
- sending paraphrased content in a private message to the user;
- using commands to interact with AI in a private chat with VK-bot.
<br><br>


## QUICK START
### Configuration
1. Create a **"config"** folder in the same directory as the bot.
2. Create the configuration file **"application.properties"** in the "config" folder.
3. In the created file, specify the following settings, be sure to specify group id, group token, url of local AI server.  
Supported endpoints: 
- http://localhost:1234/v1/chat/completions
- http://localhost:1234/v1/embeddings
- http://localhost:1234/v1/models
```
   group.id=groupId
   group.token=groupToken
   ai.local.server.url=aiLocalServerUrl
```
4. Create **"urls.txt"**, **"themes.txt"**, **"ids.txt"** in the "config" folder. 
5. In the file "urls.txt" on a separate line specify the url of group you want to watch (one line - one url).
6. In the file "themes.txt" on a separate line specify the topic for filtering content  (one line - one topic).
7. In the file "ids.txt" on a separate line specify the user id which rephreased content will be send to (one line - one id).
8. Download and install [LM Studio](https://lmstudio.ai/).
9. Launch LM Studio and go to "Search" on side panel on the left then find and download Mixtral-8x7B-Instruct-v0.1 or another model you like.
10. Go to "Server" on side panel on the left and select your downloaded model pressing "select a model to load" at the top.


### Getting Started
1. Build a project using maven.
2. Put the compiled project (group-watcher-1.0.jar) in the directory where the configuration folder (config) is located.
3. Install Java >= 17.
4. Launch the bot using the command line and the command "java –jar group-watcher-1.0.jar".
<br><br>


## USED TECHNOLOGIES <br>
### Previously used technologies
- spring-boot-starter
- hibernate-core
- spring-boot-starter-data-jpa


### New technology specifically for this project
- vk api
- LM Studio

