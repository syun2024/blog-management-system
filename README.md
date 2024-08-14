※このファイルは markdown 形式で記載しているためプレビューで開くと見やすいです。

# 完成図

これからブログ管理システムを作成していきます。  
以下 URL が完成版なので触ってみてください。  
https://blog-management-system-o636.onrender.com/

## 前提

- 課題はこのプロジェクトを上書きする形で進めてください。
  新規でプロジェクトを作成する必要はありません。

- Freeks において`spring-boot-starter-data-jpa`のライブラリを使用して、データベースとやりとりをしていましたが
  JDBC の理解を深めてもらうために、この課題では使用しませんので、ご認識お願い致します。

# 環境構築

## データベース作成

pgAdmin4 で `blog-system` というデータベースを作成してください。  
ユーザー名とパスワードは`postgres`という名称で作成してください。

## テーブル作成

データベース`blog-system` に `Blog` テーブルを`resources/db/create_blogs_table.sql`に下記のカラムを用意し作成してください。

- ID
- タイトル
- 内容
- 作成日時
- 更新日時
- 削除日時

制約に関しては必要であれば設定してください。  
また、作成日時と更新日時はデフォルトで現在時刻をセットするようにしてください。

### 挑戦

削除日時がある理由を調べてみよう！

## パッケージ作成

`blog-management-system/src/main/java/com/example/blog`配下に下記のパッケージ構成になるように作成してください。

```
└── blog
    ├── BlogApplication.java
    ├── controller
    ├── entity
    ├── repository
    ├── service
    └── util
```

# ブログ一覧取得機能

以下の手順でブログ記事の一覧取得機能を作成していきましょう。

## Entity

`Blog` テーブルのカラムに合うように Entity を実装してください。  
併せて Getter,Setter を用意してください。

Entity パッケージに`Blog.java`を作成してください。

## Repository の interface

データベースへの一覧取得機能の interface を作成してください。

repository パッケージに`BlogRepository.java`を作成してください。  
`findAll`メソッドを作成し 引数はなしで全ての Blog オブジェクトを返却してください。

## Repository の実装

データベースへの一覧取得機能を実装してください

repository パッケージに`BlogRepositoryImpl.java`を作成してください。  
`BlogRepository`を実装し、`findAll`メソッドをの中身を実装してください。  
以下は要件になります。

- 削除日が null のデータを取得
- ID が降順になるように取得

### 挑戦

try-with-resources 構文を使用して、リソースを自動的にクローズするようにしてみましょう。

## Service

ビジネスロジックを作成してください。

service パッケージに`BlogService.java`を作成してください。

`list`メソッドを作成し、Repository の`findAll`メソッドを呼び出して、全ての Blog オブジェクトを返却してください。

### 挑戦

@Autowired アノテーションを使用して、実装してみよう

## Controller

Service の`list`メソッドを呼び出して、全ての Blog オブジェクトを View に渡してください。

`src/main/resources/templates/blog/list.html`を表示してください

## 動作確認

期待通りに登録されたデータが全取得できているか確認しましょう。

以下のクエリを実行してください。

```
INSERT INTO blogs (title, content, deleted_at)
VALUES
('First Blog', 'Content of the first blog', NULL),
('Second Blog', 'Content of the second blog', NULL),
('Third Blog', 'Content of the third blog', CURRENT_TIMESTAMP + INTERVAL '1 hour');
```

サーバーを起動して、`localhost:8080/blogs`にアクセスして、2 件取得できることを確認できたら正常に実装ができています！

# ブログ記事作成機能

以下の手順でブログ記事の作成機能を作成していきましょう。

## Form

ユーザーからの入力データを受け取る Form クラスを作成してください。

form パッケージに`BlogForm.java`を作成してください。  
どのフィールドを定義するか考えてみてください。

## Repository

データベースへの保存機能の interface を作成してください。

`save`メソッドを作成し、 Blog オブジェクト を受け取り、空を返却するメソッドを作成してください。

## Repository 実装

先ほど interface で定義した内容を実装してください。

タイトルと内容を保存するクエリを作成してください。

## Service

ビジネスロジックを作成してください。

`create`メソッドを作成し、Form から Entity に詰め直して保存するように実装してください

## Controller（Get リクエスト）

ブログ作成画面のリクエストが来た際にフォームをレスポンスする実装をしてください。

以下要件です。

- `create`メソッドを作成してください。
- `/blogs/new`にリクエストが来た際に動作するようにしてください。
- `src/main/resources/templates/blog/form.html`を表示してください

## Controller（Post リクエスト）

ブログ作成のリクエストが来た際に保存する実装をしてください。

`/blogs`にリクエストが来た際に動作するようにしてください。

`blog/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、新しいブログを作成ボタンを押下してください。  
タイトルと内容を入力して保存ボタンを押下してください。  
一覧画面に遷移して、登録した情報が表示されていたら正常に実装ができております。

# ブログ記事詳細画面表示機能

以下の手順で、ブログ記事の詳細画面に遷移する機能を作成していきましょう。

## Repository の interface

ブログ記事を ID で取得するためのメソッドを定義してください。

`findById`メソッド名称で、ID を引数に取り Blog オブジェクト を返却するメソッドを定義してください。

## Repository の実装

上記で定義した`findById`メソッドを実装してください。

指定された`ID`で削除日時が`null`のブログ記事を取得するクエリを実装してください。

## Service

service パッケージに`BlogService.java`を作成してください。

`detail`メソッドを作成し、Repository の`findById`メソッドを呼び出して、ID に対応する`Blog`オブジェクトを返却してください。

## Controller

ブログ詳細画面のリクエストに対応する Controller メソッドを実装してください。

`BlogService`の`detail`メソッドを呼び出し、ブログ記事を取得してください。  
取得したブログ記事をモデルに追加し、`src/main/resources/templates/blog/detail.html`を表示してください。

## 動作確認

`localhost:8080/blogs/{id}` にアクセスし、指定された ID のブログ記事の詳細が表示されることを確認してください。

# ブログ記事更新機能

以下の手順でブログ記事の更新機能を作成していきましょう。

## Repository

`update`メソッドを作成して、 Blog オブジェクト を受け取り、空を返却するメソッドを作成してください。

## Repository 実装

先ほど interface で定義した内容を実装してください。

タイトルと内容を更新するクエリを作成し、更新日時を現在時刻に設定してください。

## Service

ビジネスロジックを作成してください。

Form から Entity に詰め直して更新するように実装してください。

## Controller（Get リクエスト）

ブログ更新画面のリクエストが来た際に、既存のデータをフォームに表示する実装をしてください。

`/blogs/{id}/edit`にリクエストが来た際に動作するようにしてください。

`src/main/resources/templates/blog/form.html`を表示してください。

## Controller（Post リクエスト）

ブログ更新のリクエストが来た際に保存する実装をしてください。

`/blogs/{id}`にリクエストが来た際に動作するようにしてください。

`blog/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、既存のデータのタイトルを押下して、詳細画面に遷移してください。  
編集ボタンを押下して、更新画面に遷移してください。

タイトルと内容を更新して保存ボタンを押下してください。  
一覧画面に遷移して、更新した情報が表示されていたら正常に実装ができております。

`localhost:8080/blogs/{id}/edit`にアクセスして、フォームにデータが表示されることを確認し、内容を変更して更新してください。  
更新後一覧画面にて内容が更新されていたら正常に実装ができています！

# ブログ記事削除機能

以下の手順でブログ記事の削除機能を作成していきましょう。

## Repository

データベースへの削除機能の interface を作成してください。

`delete`メソッドを作成して Blog の ID を受け取り、空を返却するメソッドを作成してください。

## Repository 実装

先ほど interface で定義した内容を実装してください。

削除日時を現在時刻に設定するクエリを作成してください。

## Service

ビジネスロジックを作成してください。

削除リクエストを受け取り、Repository の delete メソッドを呼び出す実装をしてください。

## Controller

削除リクエストが来た際に、削除処理を行う実装をしてください。

`/blogs/{id}/delete`にリクエストが来た際に動作するようにしてください。

削除後は`src/main/resources/templates/blog/list.html`を表示してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、詳細画面に遷移してください。
削除ボタンを押下してください

一覧画面に遷移して、該当の情報が削除されていたら正常に実装ができております。

# 提出

課題お疲れ様でした！  
CRUD 処理の基本は理解できたでしょうか？  
新規レポジトリを作成し、public 状態にして URL を教育担当者に共有をお願いします！
