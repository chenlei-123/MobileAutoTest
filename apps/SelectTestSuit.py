# -*- coding: utf-8 -*-
import sys, os
import re


def searchString(text, pattern):
    """ test: 被搜索的源字符串
        pattern: 正则表达式语法
        index: 要获取的分组结果索引
    """
    m = re.search(pattern, text)
    if m:
        return m.group(0)


def ReplaceFileContentDebugTool(filename, srcpattern, dsttext):
    '''替换文件中的内容,支持正则表达式语法'''
    file = open(filename, "r")
    lines = file.readlines()
    bFound = False
    output = ""
    for line in lines:
        findstr = searchString(line, srcpattern)
        if findstr:
            print "Find Text:" + findstr
            line = line.replace(line, dsttext)
            bFound = True
        output = output + line
    file.close()

    new_file = open(filename, "w")
    new_file.writelines(output)
    new_file.close()
    return bFound


def selectTestSuit(caseSuit):
    pomFile = os.getcwd() + '/pom.xml'

    CircleIndex = '<suiteXmlFile>res/CircleIndex.xml</suiteXmlFile>\n'
    CircleIndexNote = '<!--<suiteXmlFile>res/CircleIndex.xml</suiteXmlFile>-->\n'

    bisRelationship = '<suiteXmlFile>res/bisRelationship.xml</suiteXmlFile>\n'
    bisRelationshipNote = '<!--<suiteXmlFile>res/bisRelationship.xml</suiteXmlFile>-->\n'

    borrowingRank = '<suiteXmlFile>res/borrowingRank.xml</suiteXmlFile>\n'
    borrowingRankNote = '<!--<suiteXmlFile>res/borrowingRank.xml</suiteXmlFile>-->\n'

    getFansList = '<suiteXmlFile>res/getFansList.xml</suiteXmlFile>\n'
    getFansListNote = '<!--<suiteXmlFile>res/getFansList.xml</suiteXmlFile>-->\n'

    getFollowList = '<suiteXmlFile>res/getFollowList.xml</suiteXmlFile>\n'
    getFollowListNote = '<!--<suiteXmlFile>res/getFollowList.xml</suiteXmlFile>-->\n'

    List = '<suiteXmlFile>res/List.xml</suiteXmlFile>\n'
    ListNote = '<!--<suiteXmlFile>res/List.xml</suiteXmlFile>-->\n'

    PersonTopic = '<suiteXmlFile>res/PersonTopic.xml</suiteXmlFile>\n'
    PersonTopicNote = '<!--<suiteXmlFile>res/PersonTopic.xml</suiteXmlFile>-->\n'

    ReplyDetail = '<suiteXmlFile>res/ReplyDetail.xml</suiteXmlFile>\n'
    ReplyDetailNote = '<!--<suiteXmlFile>res/ReplyDetail.xml</suiteXmlFile>-->\n'


    if caseSuit == 'CircleIndex':
        ReplaceFileContentDebugTool(pomFile, CircleIndexNote, CircleIndex)
    elif caseSuit == 'bisRelationship':
        ReplaceFileContentDebugTool(pomFile, bisRelationshipNote, bisRelationship)
    elif caseSuit == 'borrowingRank':
        ReplaceFileContentDebugTool(pomFile, borrowingRankNote, borrowingRank)
    elif caseSuit == 'getFansList':
        ReplaceFileContentDebugTool(pomFile, getFansListNote, getFansList)
    elif caseSuit == 'getFollowList':
        ReplaceFileContentDebugTool(pomFile, getFollowListNote, getFollowList)
    elif caseSuit == 'List':
        ReplaceFileContentDebugTool(pomFile, ListNote, List)
    elif caseSuit == 'PersonTopic':
        ReplaceFileContentDebugTool(pomFile, PersonTopicNote, PersonTopic)
    elif caseSuit == 'ReplyDetail':
        ReplaceFileContentDebugTool(pomFile, ReplyDetailNote, ReplyDetail)
    elif caseSuit == 'part1':
        ReplaceFileContentDebugTool(pomFile, CircleIndexNote, CircleIndex)
        ReplaceFileContentDebugTool(pomFile, ListNote, List)
    elif caseSuit == 'part2':
        ReplaceFileContentDebugTool(pomFile, bisRelationshipNote, bisRelationship)
        ReplaceFileContentDebugTool(pomFile, borrowingRankNote, borrowingRank)
        ReplaceFileContentDebugTool(pomFile, getFansListNote, getFansList)
    elif caseSuit == 'part3':
        ReplaceFileContentDebugTool(pomFile, getFollowListNote, getFollowList)
        ReplaceFileContentDebugTool(pomFile, PersonTopicNote, PersonTopic)
        ReplaceFileContentDebugTool(pomFile, ReplyDetailNote, ReplyDetail)
    elif caseSuit == 'AllCase':
        ReplaceFileContentDebugTool(pomFile, CircleIndexNote, CircleIndex)
        ReplaceFileContentDebugTool(pomFile, ListNote, List)
        ReplaceFileContentDebugTool(pomFile, bisRelationshipNote, bisRelationship)
        ReplaceFileContentDebugTool(pomFile, borrowingRankNote, borrowingRank)
        ReplaceFileContentDebugTool(pomFile, getFansListNote, getFansList)
        ReplaceFileContentDebugTool(pomFile, getFollowListNote, getFollowList)
        ReplaceFileContentDebugTool(pomFile, PersonTopicNote, PersonTopic)
        ReplaceFileContentDebugTool(pomFile, ReplyDetailNote, ReplyDetail)


if __name__ == '__main__':
    caseSuit = sys.argv[1]
    selectTestSuit(sys.argv[1])
