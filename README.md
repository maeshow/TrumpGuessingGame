## TrumpGuessingGame

トランプ当てゲーム

## Overview

コンピュータがランダムで決めたトランプの図柄と数字をプレイヤーが当てるゲーム

## Detail

- 図柄： ハート、ダイヤ、スペード、クローバー
- 数字： A、2、3、4、5、6、7、8、9、10、J、Q、K

### インターフェース

#### CUI

実行例： 正解

``` console
トランプを選んだよ
トランプの図柄を当ててね
0:ハート
1:ダイヤ
2:スペード
3:クローバー
どれだと思う？：1
残念！ダイヤじゃないよ
どれだと思う？：2
残念！スペードじゃないよ
どれだと思う？：0
正解！図柄はハートだよ
次は数字を当ててね
どれだと思う？：4
残念！4じゃないよ
どれだと思う？：Q
残念！Qじゃないよ
どれだと思う？：J
正解！ハートのJだよ
```

## Structure Overview

- src/
    - App
        - main()
        - setCorrectByRandom()
        - getPlayerInputForTrumpMark()
        - getPlayerInputForTrumpNumber()
        - isResponseLimitRangeForMark()
        - isResponseLimitRangeForNumber()
        - isEqual(int a, int b)
        - isTrumpMarkRange()
        - isTrumpNumber()
    - Messages