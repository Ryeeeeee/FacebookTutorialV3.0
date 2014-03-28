#include "HelloWorldScene.h"
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "FacebookInterface.h"
#endif

USING_NS_CC;

using namespace std;

bool HelloWorld::isCallBack = false;
int  HelloWorld::pressedButtonNum = -1;
string HelloWorld::friendListStr = "";

Scene* HelloWorld::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = HelloWorld::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HelloWorld::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
    
    Size visibleSize = Director::getInstance()->getVisibleSize();
    Point origin = Director::getInstance()->getVisibleOrigin();

    statusLabel = LabelTTF::create("LogIn", "Arial", 30, Size(30*5,0), TextHAlignment::CENTER, TextVAlignment::TOP);
    statusLabel->setColor(Color3B::BLUE);
    statusLabel->setPosition(Point(origin.x + statusLabel->getContentSize().width/2,
                                 origin.y + visibleSize.height/2));
    
    this->addChild(statusLabel,1);
    Menu* pMenu = Menu::create(NULL,NULL);
    pMenu->setPosition(Point(visibleSize.width/2 + origin.x,visibleSize.height/2 + origin.y));
    this->addChild(pMenu,1);
    
    
    vector<string> menuVect;
    menuVect.push_back("login");
    menuVect.push_back("logout");
    menuVect.push_back("getStatus");
    menuVect.push_back("post");
    menuVect.push_back("pickFriend");
    menuVect.push_back("sendRequests");
    menuVect.push_back("uploadImage");
    
    for (int i = 0; i < menuVect.size(); i++){
        MenuItemFont* ItemFont = MenuItemFont::create(menuVect.at(i).c_str(),CC_CALLBACK_1(HelloWorld::menuCloseCallback, this));
        ItemFont->setFontSize(30);
        ItemFont->setTag(i);
        pMenu->addChild(ItemFont);
    }
    
    pMenu->alignItemsVerticallyWithPadding(10);
    
    Director::getInstance()->getScheduler()->scheduleSelector(schedule_selector(HelloWorld::FacebookCallback), this, 1.0, false);
    
    return true;
}


void HelloWorld::menuCloseCallback(Ref* pSender)
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    MenuItemFont* ItemFont = (MenuItemFont *)pSender;
    
    HelloWorld::pressedButtonNum = ItemFont->getTag();
    
    switch(HelloWorld::pressedButtonNum){
        case 0:
            FacebookInterface::login(0,"login");
            break;
        case 1:
            FacebookInterface::logout(1);
            break;
        case 2:
            statusLabel->setString(FacebookInterface::getStatus(1));
            break;
        case 3:
            FacebookInterface::postStatus(3,
                "Facebook SDK for Android",
                "Build great social apps and get more installs.",
                "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.",
                "https://developers.facebook.com/android",
                "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
            break;
        case 4:
            FacebookInterface::pickFriend(4);
            break;
        case 5:
            FacebookInterface::sendRequests(5);
            break;
        case 6:
            FacebookInterface::uploadImage(6);
        default:
            break;
        
    }
#endif
}


void HelloWorld::CallFunctionName(int cbIndex,string tstr)
{
    HelloWorld::pressedButtonNum = cbIndex;
    switch (HelloWorld::pressedButtonNum)
    {
        case 3:
            break;
        case 4:
            HelloWorld::friendListStr = tstr;
            CCLOG("HelloWorld::friendListStr=%s",HelloWorld::friendListStr.c_str());
            break;
        case 5:
            break;
        case 6:
            CCLOG("The url of image you post is :%s",tstr.c_str());
            break;
        default:
            break;
    }
    isCallBack = true;
}


void HelloWorld::FacebookCallback(float dt)
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    if (!isCallBack)
    {
        return;
    }
    switch (HelloWorld::pressedButtonNum)
    {
            isCallBack = false;
            // add Facebook3
        case 0:
        {
            statusLabel->setString("logout");
        }
            break;
        case 1:
        {
            statusLabel->setString("login");
        }
            break;
        case 2:
        {
            statusLabel->setString(FacebookInterface::getStatus(1));
        }
            break;
        case 3:
        {
            
        }
            //FacebookInterface::postStatus(3);
            break;
        case 4:
        {
            statusLabel->setString(HelloWorld::friendListStr.c_str());
        }
            break;
        case 5:
            //FacebookInterface::sendRequests(5);
            break;
        default:
            break;
    }
#endif
}
