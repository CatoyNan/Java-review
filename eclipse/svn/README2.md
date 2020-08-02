## eclipse使用svn

### 一、解决冲突

> #### 本地与远程仓库代码进行比较，产看是否有冲突

![image-20200726135901586](Untitled.assets/image-20200726135901586.png)



> #### 展示本地和远程仓库出现不同的代码

![image-20200726140729428](Untitled.assets/image-20200726140729428.png)



> #### 双击文件出现代码比对框

![image-20200726140854656](Untitled.assets/image-20200726140854656.png)



### 二、忽略某些文件

![image-20200726175906604](Untitled.assets/image-20200726175906604.png)

### 三、eclipse check out

> #### 选择导入

![image-20200726181116435](Untitled.assets/image-20200726181116435.png)

> #### 将资源作为项目导入

![image-20200726181226845](Untitled.assets/image-20200726181226845.png)

> #### 将项目转化未maven项目

![image-20200726181439418](Untitled.assets/image-20200726181439418.png)

### 四、新建分支

#### 1.从trunk创建分支

> #### 选择远程仓库

![image-20200726184003670](Untitled.assets/image-20200726184003670.png)



![image-20200726184039007](Untitled.assets/image-20200726184039007.png)

#### 2.从分支创建分支

> #### 选中某个分支右键

![image-20200726191858864](Untitled.assets/image-20200726191858864.png)

![image-20200726191926718](Untitled.assets/image-20200726191926718.png)



### 五、将某个分支的代码合并到某个分支

> 首先提交代码，避免代码丢失

![image-20200726193252213](Untitled.assets/image-20200726193252213.png)



> 点击merge

![image-20200726193325560](Untitled.assets/image-20200726193325560.png)

> 选择需要合过来的分支

![image-20200726193520472](Untitled.assets/image-20200726193520472.png)

> 选择需要合并的版本

![image-20200726194556501](Untitled.assets/image-20200726194556501.png)



> 查看有冲突的文件

![image-20200726195712578](Untitled.assets/image-20200726195712578.png)



> 解决冲突

![image-20200726195757881](Untitled.assets/image-20200726195757881.png)



> 标记为已解决

![image-20200726195941194](Untitled.assets/image-20200726195941194.png)

### 六、切换分支

> 首先提交一下代码，避免代码丢失

![image-20200726193252213](Untitled.assets/image-20200726193252213.png)

> 切换分支

![image-20200726195233505](Untitled.assets/image-20200726195233505.png)