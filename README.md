# 自動化記帳
## 目標
目標有四項
1. 整理與運用目前所會的技能，學習新技能的測試專案
2. 由於每次消費都要手動記帳太麻煩了，因此設計一個服務用於讓一切消費自動記帳，如果遇到額外消費再手動記帳．
3. 做起來之後看有沒有機會與銀行或郵局的 open API 串接，做到信用卡刷卡自動記帳及自動轉帳繳學貸
4. 感覺很好玩

## 如何使用
1. 需要先建立 DB，由於開發時使用 mariaDB 因此建議使用 mariaDB
2. 需要安裝 redis
3. 新增 application-dev.yml，並且設定 datasource 和 redis 連線
4. 使用 IDE 啟動，或找到 AccountingApplication.java 使用 main 方法啟動，或者使用 docker

## 專案 loadMap（打勾表示已經達成）
* [x] 完成 spring boot + mybatis
* [x] 使用 spring doc 建立 swagger
* [x] 學習與導入 redis 為將來登入系統做準備
* [x] 服務內部的登入系統(無使用 salt，預計於 spring security 加入這部分)
* [ ] 導入 spring security
* [ ] 優化 swagger 顯示內容
* [ ] 使用 jenkins 和 k8s （需要學習）
* [ ] 使用 aws 的 domain name
* [ ] 使用 Let's Encrypt 做出 https (SSL)
* [ ] 整合 line bot 用聊天室暫時用於記帳
* [ ] 使用 Typescript + react 製作可使用的前端 （目前兩個都學完了，但尚未有實際應用經驗）
* [ ] 導入 spring ai 用自然語言記帳（完全沒有概念，但一定很有趣）