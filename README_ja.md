Setenv Builder plugin
=====================

変数を定義するビルド手順を追加するJenkinsプラグイン

これはなに？
------------

Setenv Builder は、「変数を定義する」ビルド手順を追加する [Jenkins](http://jenkins-ci.org/) プラグインです: 
* それ以降のビルド手順で利用可能な変数を定義します。
* 変数の定義方法を以下から選べます:
	* リストで指定: 変数の名前と値をテキストフィールドで1つずつ指定します。
	* テキストエリアで指定: テキストエリアで「NAME=VALUE」の形式で1行ずつ設定する変数を指定します。
* 変数の定義方法は[Jenkinsの拡張ポイント機能] (https://wiki.jenkins-ci.org/display/JENKINS/Extension+points) を使用して新しいものを追加することができます。

制限事項
--------

* ビルドのパラメータは上書きすることができません。

拡張ポイント
------------

新しい変数の定義方法を作る場合は、`SetenvConfig` 抽象クラスを継承し、以下のメソッドをオーバーライドします:

```java
public abstract String JobcopyOperation::perform(String xmlString, String encoding, EnvVars env, PrintStream logger);
```

もしくは、`AbstractSetenvConfig` 抽象クラスを継承し、変数名と変数値のリストを作成して以下のメソッドを設定します:

```java
public void AbstractSetenvConfig::setSetenvEntryList(List<SetenvEntry> setenvEntryList);

```

TODO
----

* テストを書く
* コメントを英語化する
* [jenkins-ci.orgでプラグインを公開する] (https://wiki.jenkins-ci.org/display/JENKINS/Hosting+Plugins)
