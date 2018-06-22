# 数据库设计与应用开发大作业实验报告

#### 小组成员

 - 张金亮
 - 周远笛   16340311    
 - 洪梓聪   15344015

## 实验简介

### 开发目的

对于本次数据库设计与应用开发，我们小组决定开发一个ToDoList类型基于安卓平台的软件。在开发中对数据的存储应用ORM型数据库，并结合课程所学对数据库的关系以及属性进行设计。此次实验中不仅锻炼了小组开发合作能力，还制作出了未来可以给我们规划学习和生活提供便利的软件。

### 想法来源

市场上的ToDoList软件都是比较简单的设定目标，完成之后就直接把这个目标删除，这样并没有给用户完成任务后的快感，所以我们希望开发一个新的App，可以记录之前完成的所有任务，并支持查询之前的任务，还能显示出完成情况和每天的进步状况。

相比于其他同类竞品，我们这个设计的主要优势在于使用多种成功学、心理学上的驱动方式（包括成就感驱动、“一步之遥”驱动、初衷驱动等等）让人坚持不懈地努力。

## 实验环境

- JDK 1.8.0

- Android 4.4+

- Android Studio 3.1

- greenDAO 3.2.2

  > Android中提供了一个占用内存极小的关系型数据库SQLite和许多操作SQLite的API，但我们仍需要手动去编写许多SQL语句来操作数据库，这大大的增加了思维的复杂度，由于我们小组人员较少和开发时间较短，所以我们决定采用「greenDAO」—— 一个SQLite数据库的Android ORM，来进行软件数据库建设。
  >
  > 
  >
  > 选择greenDAO数据库是考虑到其易用性以及轻量级的特性，由于其占用很小的内存但却有不俗的表现，对于我们三人的小开发团队是个不错的选择。
  >
  > ![CxvU6P.png](https://s1.ax1x.com/2018/06/19/CxvU6P.png)

- Junit 4.12

- Butterknife 8.5.1

  > 作为触屏手机的app不得不处理许多繁琐的触屏点击事件，而我们使用butterknife可以用View绑定Click处理事件，简化代码。

## 实验原理

本实验在Android平台上利用MVC架构，并使用Butterknife加速开发，Junit测试设计出一款便于学生进行日常时间管理的软件。在数据库方面，我们采用了greenDAO来将Model中的EverydayTask（每日任务）和OnedayTask（当日任务）两个对象映射成数据库中的表，并且添加增删改查的操作行为，同时利用greenDAO中内置的优化方法去提升数据库的速度，而我们不用传统数据库的原因是因为我们团队较小，时间比较紧急，利用ORM型数据库可以有效的缩短开发时间，降低开发难度。

## 数据库设计

在SaltedFish

1. EverydayTask

   构造函数有两种....（吹不下去了）

   

   在每日任务部分，需要先创建数据表，设置表的名字为EVERY_DAY_TASK，其属性有

   - id - 唯一标记事件
   - detail - 时间细节
   - mEndTime - 事件结束时间
   - isComplete - 事件结束与否

   数据库最基本的创建表操作在greenDAO中，我们调用了`createTable`函数来实现。

   ```java
   public class EveryDayTaskDao extends AbstractDao<EveryDayTask, Long> {
   
       public static final String TABLENAME = "EVERY_DAY_TASK";
   
       /**
        * Properties of entity EveryDayTask.<br/>
        * Can be used for QueryBuilder and for referencing column names.
        */
       public static class Properties {
           public final static Property Id = new Property(0, Long.class, "id", true, "_id");
           public final static Property Detail = new Property(1, String.class, "detail", false, "DETAIL");
           public final static Property MEndTime = new Property(2, long.class, "mEndTime", false, "M_END_TIME");
           public final static Property IsComplete = new Property(3, boolean.class, "isComplete", false, "IS_COMPLETE");
           public final static Property Name = new Property(4, String.class, "name", false, "NAME");
           public final static Property IsDetailTime = new Property(5, boolean.class, "isDetailTime", false, "IS_DETAIL_TIME");
           public final static Property Combos = new Property(6, int.class, "combos", false, "COMBOS");
       }
   
   
       public EveryDayTaskDao(DaoConfig config) {
           super(config);
       }
       
       public EveryDayTaskDao(DaoConfig config, DaoSession daoSession) {
           super(config, daoSession);
       }
   
       /** Creates the underlying database table. */
       public static void createTable(Database db, boolean ifNotExists) {
           String constraint = ifNotExists? "IF NOT EXISTS ": "";
           db.execSQL("CREATE TABLE " + constraint + "\"EVERY_DAY_TASK\" (" + //
                   "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                   "\"DETAIL\" TEXT," + // 1: detail
                   "\"M_END_TIME\" INTEGER NOT NULL ," + // 2: mEndTime
                   "\"IS_COMPLETE\" INTEGER NOT NULL ," + // 3: isComplete
                   "\"NAME\" TEXT," + // 4: name
                   "\"IS_DETAIL_TIME\" INTEGER NOT NULL ," + // 5: isDetailTime
                   "\"COMBOS\" INTEGER NOT NULL );"); // 6: combos
       }
   
       /** Drops the underlying database table. */
       public static void dropTable(Database db, boolean ifExists) {
           String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVERY_DAY_TASK\"";
           db.execSQL(sql);
       }
   
       @Override
       protected final void bindValues(DatabaseStatement stmt, EveryDayTask entity) {
           stmt.clearBindings();
    
           Long id = entity.getId();
           if (id != null) {
               stmt.bindLong(1, id);
           }
    
           String detail = entity.getDetail();
           if (detail != null) {
               stmt.bindString(2, detail);
           }
           stmt.bindLong(3, entity.getMEndTime());
           stmt.bindLong(4, entity.getIsComplete() ? 1L: 0L);
    
           String name = entity.getName();
           if (name != null) {
               stmt.bindString(5, name);
           }
           stmt.bindLong(6, entity.getIsDetailTime() ? 1L: 0L);
           stmt.bindLong(7, entity.getCombos());
       }
   
       @Override
       protected final void bindValues(SQLiteStatement stmt, EveryDayTask entity) {
           stmt.clearBindings();
    
           Long id = entity.getId();
           if (id != null) {
               stmt.bindLong(1, id);
           }
    
           String detail = entity.getDetail();
           if (detail != null) {
               stmt.bindString(2, detail);
           }
           stmt.bindLong(3, entity.getMEndTime());
           stmt.bindLong(4, entity.getIsComplete() ? 1L: 0L);
    
           String name = entity.getName();
           if (name != null) {
               stmt.bindString(5, name);
           }
           stmt.bindLong(6, entity.getIsDetailTime() ? 1L: 0L);
           stmt.bindLong(7, entity.getCombos());
       }
   
       @Override
       public Long readKey(Cursor cursor, int offset) {
           return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
       }    
   
       @Override
       public EveryDayTask readEntity(Cursor cursor, int offset) {
           EveryDayTask entity = new EveryDayTask( //
               cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
               cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // detail
               cursor.getLong(offset + 2), // mEndTime
               cursor.getShort(offset + 3) != 0, // isComplete
               cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
               cursor.getShort(offset + 5) != 0, // isDetailTime
               cursor.getInt(offset + 6) // combos
           );
           return entity;
       }
        
       @Override
       public void readEntity(Cursor cursor, EveryDayTask entity, int offset) {
           entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
           entity.setDetail(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
           entity.setMEndTime(cursor.getLong(offset + 2));
           entity.setIsComplete(cursor.getShort(offset + 3) != 0);
           entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
           entity.setIsDetailTime(cursor.getShort(offset + 5) != 0);
           entity.setCombos(cursor.getInt(offset + 6));
        }
       
       @Override
       protected final Long updateKeyAfterInsert(EveryDayTask entity, long rowId) {
           entity.setId(rowId);
           return rowId;
       }
       
       @Override
       public Long getKey(EveryDayTask entity) {
           if(entity != null) {
               return entity.getId();
           } else {
               return null;
           }
       }
   
       @Override
       public boolean hasKey(EveryDayTask entity) {
           return entity.getId() != null;
       }
   
       @Override
       protected final boolean isEntityUpdateable() {
           return true;
       }
       
   }
   ```

2. OnedayTask

## 实验结果

这个界面是我们设计的核心部分，如软件设计的灵感来源所述，完成事情之后带来的成就感是驱动之后继续坚持习惯/打卡的源动力之一，而下图中的界面即为成果的展示。（可以参考Github中commit次数统计图）

![成果统计.png](https://github.com/hongzicong/SaltedFish/blob/master/report/1.png?raw=true)

下图这个界面是基本的TodoList，每日任务部分只是做了一个比较简单的TodoList，完成即打勾的形式。但下半部分的习惯养成是本app的亮点之一，前面的数字为待完成的天数，每天看到离目标更近一些会让人在坚持打卡之余更有前进的动力。

![Todo.png](https://github.com/hongzicong/SaltedFish/blob/master/report/2.png?raw=true)

加号图标为我们在Todo页面提供了快捷方便的添加各种事项的功能。



![交互图标.png](https://github.com/hongzicong/SaltedFish/blob/master/report/3.png?raw=true)

对于“养成习惯”的事件，我们添加了“想养成这个习惯的原因”一栏，目的是让人“不忘初心、善始善终”。

![4.png](https://github.com/hongzicong/SaltedFish/blob/master/report/4.png?raw=true) 

![5.png](https://github.com/hongzicong/SaltedFish/blob/master/report/5.png?raw=true) 

## 实验反思与改进

## 参考文献