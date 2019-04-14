# Java五子棋游戏

[TOC]

本项目使用Java开发。实现了双人对战、人机对战、联机对战等功能。

软件两大特点是：智能、网络。智能让软件在条件局限的情况下有了一种新的选择。联机则实现了数据的远程传输，增加了用户更加真实的体验。因为JAVA的跨平台性，使它在不同平台体验成为一个特色，使资源得到最大的利用。

**开发环境**：Eclipse+Jdk1.7

- 项目介绍
  - 双人模式功能
  - 人机模式功能
  - 联机模式功能
  - 悔棋功能
  - 新局功能
  - 先后手选择
  - 主动认输功能
  - 退出软件

## 项目介绍

### 双人模式功能

用户可以通过点击界面上“双人”按钮，在弹出的提示框中点击确定，即可在界面上的棋盘区域点击进行落子。在游戏开始后，如果有一方胜出，系统会弹出提示框提醒用户胜负情况，并在棋盘上绘制一条黑线，提示用户在五子相连的具体位置。

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image002.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image004.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image006.jpg?raw=true)

### 人机模式功能

用户可以通过点击界面上“人机”按钮，在弹出的提示框中点击确定，即可在界面上的棋盘区域点击进行落子。在游戏开始后，系统会根据棋盘中已有的棋子，模拟人的思维落子。如果有一方胜出，系统会弹出提示框提醒用户胜负情况，并在棋盘上绘制一条黑线，提示用户在五子相连的具体位置。

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image008.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image010.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image012.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image014.jpg?raw=true)

### 联机模式功能

用户可以通过点击界面上“联机”按钮，在弹出的提示框中点击确定，在之后弹出的窗口中可以选择“创建游戏”和“加入游戏”， “创建游戏”总要先于“加入游戏”出现，当一方创建游戏，另一方即可加入游戏。即可在界面上的棋盘区域点击进行落子，在游戏开始后。如果有一方胜出，系统会弹出提示框提醒用户胜负情况，并在棋盘上绘制一条黑线，提示用户在五子相连的具体位置。

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image016.jpg?raw=true)

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image018.jpg?raw=true)

### 悔棋功能

用户在三种模式下体验游戏时，若对当前自己落子不满意，可以通过该功能来悔棋，以此达到悔棋的目的。

### 新局功能

用户在游戏进行时，若对当前模式不满意，或者对当前局势不满意，可以通过点击“新局”来重新开始游戏；当点击“新局”后，游戏的所有设置都会重置，故需要用户重新选择游戏的对战模式或其他功能。

### 先后手选择

为了满足用户需求，此游戏可以自主选择游戏的先后手。点击“菜单”，在弹出的对话框中，点击“顺序选择”，根据相应的提示，即可完成先后手的选择，选择后，在每种模式下都会遵守此次选择的顺序。

### 主动认输功能

用户在游戏中时，可以再游戏未结束前，确定胜负方。点击“菜单”，在弹出的对话框中，点击“主动认输”，选择后，在提示框中的信息即为当前获胜方。

### 退出软件

用户打开软件后，无论当前处于什么模式，除了点击界面的右上方退出软件，本软件还为用户提供了另一种选择，点击“菜单”，在弹出的对话框中，点击“退出游戏”，选择后，即可退出游戏。

![clip_image002.jpg](https://github.com/Grootzz/Gobang/blob/master/assets/clip_image020.jpg?raw=true)