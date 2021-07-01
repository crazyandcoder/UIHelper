# UIHelper 集合
UIHelper 是一个 UI 框架合集，其中包含了很多 UI 实现样式。目前支持以下 UI ：

## 1 使用方式
### 1.1 在 project 的 build.gradle 中配置 jitpack 库

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### 1.1 在 module 的 build.gradle 中配置引用库

```
	dependencies {
	        implementation 'com.github.crazyandcoder:UIHelper:1.0.0'
	}
```
## 2 组件合集
## 2.1 日历组件
日历组件包含周日历和月日历，周日历和月日历可以设置显示范围，同时提供快捷返回今天的操作，周日历统一提供切换到月日历的方法。

![日历组件](https://img-blog.csdnimg.cn/20210701170614930.gif#pic_center)
