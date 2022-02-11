# -*- coding: utf-8 -*-
"""
参考：PythonとOCRエンジンで画像から文字を認識する
https://qiita.com/eiji-noguchi/items/c19c1e125eaa87c3616b
"""

from PIL import Image
import pyocr

# OCRエンジンを取得
engines = pyocr.get_available_tools()
engine = engines[0]

# 対応言語取得
langs = engine.get_available_languages()
print("対応言語:",langs) # ['eng', 'jpn', 'osd']

# 画像の文字を読み込む
txt = engine.image_to_string(Image.open('pic1.jpg'), lang="jpn")
print(txt)
