#BetterArrows v0.1

`Weapon` `Improvement` `Arrow` `TippedArrow`

調整藥箭效果，使其「可用」

## 基礎

#### 藥箭
> 藥箭效果的持續時間是相應的藥水的1⁄8，持續時間不會被弓的強力影響。
>
> 箭的狀態效果與藥水正常的效果相同。


    | 藥水 | 噴濺藥水 | 滯留藥水 | 藥水箭
:-- | :-: | :-: | :-: | :-:
立即效果（比例） | 2 | 2[¹](#note1) | 1 | 1
持續效果（比例） | 8 | 8[¹](#note1) | 2 | 1

##### 釀造藥水不會同時擁有立即效果或持續效果
    
<a name="note1"></a>
###### 註¹ 效果以中心為 100% 線性減少到 4 個方塊外的 0%

## 調整

大部分藥箭不再造成直接傷害，包含直接傷害將特別標註。

被不具直接傷害之藥箭擊中時將受到 `0` 點傷害，並且不被擊退。

---
#### 新合成公式

##### 藥箭合成

> ![recipe-tipped-arrows]
>
> 調整合成公式，改由一般藥水、燧石、骨灰、四支箭矢合成
>
> 類型為「持續」的藥箭，持續時間調整為原來的 `2` 倍

---
#### 個別調整

##### 藥箭種類 ![arrow] - 類型
> 
> __藥箭原始效果__
> > 藥箭新特性

##### 回復之箭 ![arrow-regen] - 持續
> > 短時間之內獲得額外強化效果
>
> __回復 (0:05) --> (0:11)__
> > 額外獲得 (+1.5 生命/秒)，持續 2 秒 (![heart]![half_heart]/2s)
> >
> > 13 秒內回復 6 (![heart]![heart]![heart]) 生命
>
> __回復 II (0:02) --> (0:05)__
> > 額外獲得 (+3.0 生命/秒)，持續 2 秒 (![heart]![heart]![heart]/2s)
> >
> > 7 秒內回復 9 (![heart]![heart]![heart]![heart]![half_heart]) 生命
>
> __回復 (0:11) --> (0:22)__
> > 額外獲得 (+1.5 生命/秒)，持續 4 秒 (![heart]![heart]![heart]/4s)
> >
> > 26 秒內回復 12 (![heart]![heart]![heart]![heart]![heart]![heart]) 生命

###### 迅捷之箭 ![arrow-speed] - 持續
###### 抗火之箭 ![arrow-fire-resistance] - 持續
##### 治療之箭 ![arrow-instant-heal] - 立即
> > 依照弓的 `強力` 等級以及 `蓄力時間` 獲得額外治療量  
> > 若目標為不死生物，擊中時造成 (+1.5 治療量) 傷害
>
> __立即治療 (![heart])__
> > 基礎治療量 2 (![heart])
> >
> > _拉滿弓_ 時獲得額外 2 (![heart]) ~ 5 (![heart]![heart]![half_heart]) 治療量  
> > `火焰` 附魔提升 2 (![heart]) 額外治療量
>
> __立即治療 II (![heart]![heart])__
> > 基礎治療量 4 (![heart]![heart])
> >
> > _拉滿弓_ 時獲得額外 4 (![heart]![heart]) ~ 11 (![heart]![heart]![heart]![heart]![heart]![half_heart]) 治療量  
> > `火焰` 附魔提升 4 (![heart]![heart]) 額外治療量

###### 夜視之箭 ![arrow-night-vision] - 持續
###### 力量之箭 ![arrow-strength] - 持續
###### 跳躍提升之箭 ![arrow-jump] - 持續
###### 隱形之箭 ![arrow-invisibility] - 持續
##### 劇毒之箭 ![arrow-poison] - 持續
> > 依照弓的 `強力` 等級以及 `蓄力時間` 造成穿透傷害  
> > 對不死生物仍能造成穿透傷害，但無法使其中毒
>
> __中毒 (0:05) --> (0:11)__ & __中毒 (0:11) --> (0:22)__
> > _拉滿弓_ 時造成 2 (![heart]) ~ 5 (![heart]![heart]![half_heart]) 穿透傷害
>
> __中毒 II (0:02) --> (0:05)__
> > _拉滿弓_ 時造成 3 (![heart]![half_heart]) ~ 7 (![heart]![heart]![heart]![half_heart]) 穿透傷害

###### 虛弱之箭 ![arrow-weakness] - 持續
###### 緩速之箭 ![arrow-slowness] - 持續
##### 傷害之箭 ![arrow-instant-damage] - 立即
> > 繼承原有傷害，並依照弓的 `強力` 等級以及 `蓄力時間` 獲得額外傷害  
> > 若目標為不死生物，額外傷害轉為傷害減免，當總傷害小於零將回復其生命
>
> __立即傷害 ( ![heart]![half_heart] )__
> > _拉滿弓_ 時獲得額外 2 (![heart]) ~ 5 (![heart]![heart]![half_heart]) 傷害
>
> __立即傷害 II ( ![heart]![heart]![heart] )__
> > _拉滿弓_ 時獲得額外 4 (![heart]![heart]) ~ 11 (![heart]![heart]![heart]![heart]![heart]![half_heart]) 傷害

##### 水下呼吸之箭 ![arrow-water-breathing] - 持續
> > 擊中時恢復已損失的氧氣槽

###### 幸運之箭 ![arrow-luck] - 持續

## 參考資源
- [Minecraft - 箭](http://minecraft-zh.gamepedia.com/%E7%AE%AD)
- [Minecraft - 藥水](http://minecraft-zh.gamepedia.com/%E8%8D%AF%E6%B0%B4)
- [Minecraft - 噴濺藥水](http://minecraft-zh.gamepedia.com/%E5%96%B7%E6%BA%85%E8%8D%AF%E6%B0%B4)
- [Minecraft - 滯留藥水](http://minecraft-zh.gamepedia.com/%E6%BB%9E%E7%95%99%E8%8D%AF%E6%B0%B4)

[recipe-tipped-arrows]: recipes/better-arrows/tipped-arrows.gif "new TippedArrow recipe"

[heart]: status/heart.png "Heart"
[half_heart]: status/half-heart.png "Half heart"

[arrow]: items/arrows/arrow.png "arrow"
[arrow-regen]: items/arrows/arrow-regen.png "arrow-regen"
[arrow-speed]: items/arrows/arrow-speed.png "arrow-speed"
[arrow-fire-resistance]: items/arrows/arrow-fire-resistance.png "arrow-fire-resistance"
[arrow-instant-heal]: items/arrows/arrow-instant-heal.png "arrow-instant-heal"
[arrow-night-vision]: items/arrows/arrow-night-vision.png "arrow-night-vision"
[arrow-strength]: items/arrows/arrow-strength.png "arrow-strength"
[arrow-jump]: items/arrows/arrow-jump.png "arrow-jump"
[arrow-invisibility]: items/arrows/arrow-invisibility.png "arrow-invisibility"
[arrow-poison]: items/arrows/arrow-poison.png "arrow-poison"
[arrow-weakness]: items/arrows/arrow-weakness.png "arrow-weakness"
[arrow-slowness]: items/arrows/arrow-slowness.png "arrow-slowness"
[arrow-instant-damage]: items/arrows/arrow-instant-damage.png "arrow-instant-damage"
[arrow-water-breathing]: items/arrows/arrow-water-breathing.png "arrow-water-breathing"
[arrow-luck]: items/arrows/arrow-luck.png "arrow-luck"
